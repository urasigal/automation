package com.zixi.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;

import com.zixi.httpserver.MyHandler;


//Class summary: Register a channel with selector line: 136

public class FLV implements Runnable {
	// The host:port combination to connect to
	private InetAddress hostAddress;
	private int port;

	// Debug flag
	// ####################################################################################
	private boolean debug = false; // false when no debugger is needed.
	// ####################################################################################

	// UDPClient udpClient = new UDPClient();

	// A Selector is where
	// you register your interest in various I/O events, and it is the object
	// that tells you when
	// those events occur.
	private Selector selector;

	// The buffer into which we'll read data when it's available
	// java.nio.ByteBuffer
	// Capacity is 8192 bytes.
	// Initial position is 0
	// Initial Limit 8192
	private ByteBuffer readBuffer = ByteBuffer.allocate(90000); 

	// A list of ChangeRequest instances each of which contains a triple of
	// (SocketChannel socket, int type, int ops)
	private List pendingChanges = new LinkedList();

	// Maps a SocketChannel to a list of ByteBuffer instances
	private Map pendingData = new HashMap();
	
	
	
	// This is a associative array of channels to queues.
	private Map<SocketChannel, Queue<byte[]>> socketToQueue = new HashMap<>();
	
	private Map<MyHandler, SocketChannel> handlerToSocket = new HashMap<>();
	

	private Deque<SelectionKey> deque = new ArrayDeque<SelectionKey>();
	

	// Constructor - Set the address and the port constructor's arguments to the
	// appropriate parameters, creates a Selector object.
	public FLV(InetAddress hostAddress, int port) throws IOException 
	{
		this.hostAddress = hostAddress;
		this.port = port;
		this.selector = this.initSelector();
	}

	// Parameters:
	// "byte[] data" - a data that will be sent.
	public void send(byte[] data, Queue<byte[]> dataToReceive, MyHandler myHandler) throws IOException {
		
		// Channels are analogous to streams in the original I/O package.
		// All data that goes anywhere (or comes from anywhere) must pass
		// through a Channel object.
		// A Java NIO SocketChannel is a channel that is connected to a TCP
		// network socket.
		// It is Java NIO's equivalent of Java Networking's Sockets.
		SocketChannel socket = this.initiateConnection(); // Here the socket is initialized one per request
		Queue<byte[]> dataQueue = dataToReceive;
		
		synchronized(socketToQueue)
		{
			socketToQueue.put(socket, dataQueue);
			handlerToSocket.put(myHandler, socket);
			myHandler.setHandlerToSocket(handlerToSocket); 
		}

		String decoded = new String(data, "UTF-8");
		if (debug == true)
			System.out.println("Data to be send " + decoded);

		// pendingData - this is a data structure that associates a socket to a
		// data that must be sent: HashMap(SocketChannel socket, List queue).
		synchronized (this.pendingData) 
		{
			List<ByteBuffer> queue = (List) this.pendingData.get(socket);
			if (queue == null) 
			{
				queue = new ArrayList<ByteBuffer>();
				this.pendingData.put(socket, queue);
			}
			queue.add(ByteBuffer.wrap(data)); // /???????????????
		}

		// Finally, wake up our selecting thread so it can make the required
		// changes
		this.selector.wakeup();
	}

	public void run() 
	{
		while (true) 
		{
			try {
				// Process any pending changes. Getting a monitor of a "pendingChanges" object.
				synchronized (this.pendingChanges)
				{
					// Get iterator for the pendingChanges collection
					// (LinkedList of a channels with ops and type).
					// Go through the List and set an appropriate interest of operation.
					Iterator changes = this.pendingChanges.iterator(); // pendingChanges  --> LinkedList
					while (changes.hasNext())
					{ //over the ChangeRequest list  that associates the socket to some configuration data.
						ChangeRequest change = (ChangeRequest) changes.next();
						switch (change.type) 
						{
							case ChangeRequest.CHANGEOPS:
								SelectionKey key = change.socket.keyFor(this.selector);
								key.interestOps(change.ops);
								break;
								
								// register a socket to the  selector object.  In this case the "change.ops" equals to  the "OP_CONNECT"
							case ChangeRequest.REGISTER:
								change.socket.register(this.selector, change.ops); // In this case the "change.ops" equals to SelectionKey.OP_CONNECT
								break;
						}
					}
					//Clear the List
					this.pendingChanges.clear();
				}

				// Wait for an event of the registered channels: This is a
				// blocking method - which means that this method doesn't return
				// immediately
				
					int readyCount = this.selector.select(); // readyCount - counts  a channels for which the event had occurred.
					
					synchronized(deque)
					{ 
					if (debug == true)
						System.out.println("Nmber of ready streams " + readyCount);
					
					for(SelectionKey key : this.selector.selectedKeys())
					{
						// Check what event is available and deal with it
						if (key.isConnectable()) 
						{ // finish a connection procedure
							this.finishConnection(key);
						} 
						else if (key.isReadable())
						{
							this.read(key);
						} 
						else if (key.isWritable()) 
						{
							this.write(key);
						}
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} //End of Thread while loop.
	}

	private void read(SelectionKey key) throws IOException 
	{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		Queue<byte[]> queue = (Queue<byte[]>)socketToQueue.get(socketChannel);
	
		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();
		int numRead = -1;
		// Attempt to read off the channel
		try {
			if (queue != null )
			{
				synchronized(queue)
				{
					numRead = socketChannel.read(this.readBuffer);
					readBuffer.flip( );
					byte[] receivedData = new byte[numRead];
					this.readBuffer.get(receivedData , 0, numRead);
					if(queue.size() > 50)
					{
						queue.remove();
					}
					
					queue.add(receivedData);
					queue.notifyAll();
				}
			}
			if (debug == true) {
				System.out.println("Read buffer attributes: " + " Capacity: "
						+ readBuffer.capacity() + "; Limit: "
						+ readBuffer.limit() + "; Position: "
						+ readBuffer.position());
			}
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			key.cancel();
			socketChannel.close();
			return;
		}

		if (numRead == -1) {
			// Remote entity shut the socket down cleanly. Do the
			// same from our end and cancel the channel.
			key.channel().close();
			key.cancel();
			return;
		}

		if (debug == true)
			System.out.println("In the read funtion");
		this.handleResponse(socketChannel, this.readBuffer.array(), numRead);
	}

	private void handleResponse(SocketChannel socketChannel, byte[] data,
			int numRead) throws IOException {
		// Make a correctly sized copy of the data before handing it
		// to the client
		byte[] rspData = new byte[numRead];
		//System.arraycopy(data, 0, rspData, 0, numRead);

		if (debug == true) {
			// System.out.println("Received data from the server (broadcaster) "
			// + new String(data, "UTF-8"));
		}
	}

	// Writes the request body to a buffer.
	// In this case a request body is stored in the "pendingData" object's
	// HashMap instance variable.
	private void write(SelectionKey key) throws IOException 
	{
		// Debugging printing.
		if (debug == true)
			System.out.println("In the write function");
		SocketChannel socketChannel = (SocketChannel) key.channel();
		synchronized (this.pendingData) 
		{
			// Retrieve the data that must be sent according to its channel.
			List queue = (List) this.pendingData.get(socketChannel);
			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0); // (ByteBuffer)
															// queue.get(0)
															// returns a
															// ByteBuffer that
															// associated with
															// the specific
															// Socket channel.
				if (debug == true) {
					System.out.println("Write to buffer "
							+ new String(buf.array(), "UTF-8"));
					System.out.println("Write buffer Attributes: "
							+ " Capacity: " + buf.capacity() + " Limit: "
							+ buf.limit() + " Position: " + buf.position());
				}
				int writeAmmount = socketChannel.write(buf);
				if (buf.remaining() > 0) {
					// ... or the socket's buffer fills up
					break;
				}
				queue.remove(0);
			}

			if (queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ); // set the OP_READ
														// interest.
			}
		}
	}

	private void finishConnection(SelectionKey key) throws IOException {

		// A SelectionKey represents this registration of this channel with this
		// Selector
		SocketChannel socketChannel = (SocketChannel) key.channel();

		// Finish the connection. If the connection operation failed
		// this will raise an IOException.
		try {
			socketChannel.finishConnect();
		} catch (IOException e) {
			// Cancel the channel's registration with our selector
			System.out.println(e);
			key.cancel();
			return;
		}

		// Sets this key's interest set to the given value.
		// This method may be invoked at any time. Whether or not it blocks, and
		// for how long, is implementation-dependent.
		key.interestOps(SelectionKey.OP_WRITE);
	}

	private SocketChannel initiateConnection() throws IOException {
		// Create a non-blocking socket channel - in order to use with a selector.
		// Channels are analogous to streams in the original I/O package.
		SocketChannel socketChannel = SocketChannel.open();
		
		// Set the channel to be in nonblocking mode
		socketChannel.configureBlocking(false);

		// Connects this channel's socket.
		socketChannel.connect(new InetSocketAddress(this.hostAddress, this.port));

		// Queue a channel registration since the caller is not the
		// selecting thread. As part of the registration we'll register
		// an interest in connection events. These are raised when a channel
		// is ready to complete connection establishment.
		synchronized (this.pendingChanges) // pendingChanges it is a LinkedList.
		{ 
		// Postpone the registration. Set the new established socketChannels to an ArrayList of a
		// The ChangeRequest objects looks like: 
												// public static final int
												// REGISTER = 1;
												// public static final int
												// CHANGEOPS = 2;
												// public SocketChannel socket;
												// public int type;
												// public int ops;
			this.pendingChanges.add(new ChangeRequest(socketChannel,
					ChangeRequest.REGISTER, SelectionKey.OP_CONNECT));
		}

		return socketChannel;
	}

	
	private Selector initSelector() throws IOException {
		// Create a new selector - The central object in asynchronous I/O is
		// called the Selector. A Selector is where
		// you register your interest in various I/O events, and it is the
		// object that tells you when
		// those events occur.
		return SelectorProvider.provider().openSelector();
	}
}
