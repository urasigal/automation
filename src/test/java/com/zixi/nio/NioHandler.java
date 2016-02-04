package com.zixi.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NioHandler {

	private Deque<SelectionKey> deque = new ArrayDeque<SelectionKey>();
	
	private List<SelectionKey> keyList = new ArrayList();
	 
	private Map pendingData = new HashMap();
	
	protected Iterator selectedKeys;
	
	private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
	
	public Map getPendingData() {
		return pendingData;
	}
	
	public void setPendingData(Map pendingData) {
		this.pendingData = pendingData;
	}

	
	public Iterator getSelectedKeys() {
		return selectedKeys;
	}

	public void setSelectedKeys(Deque<SelectionKey> deque) {
		synchronized(deque){
			this.deque = deque;
		}
	}

	
	public void treatRedy() throws IOException
	{
		// Ensure a consistent behavior of retrieving of a particular SelectionKey object
		synchronized(deque)
		{
			while( deque.size() <= 0)
			{
				try {
					deque.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		SelectionKey key;
		
		
		while(true)
		{
			synchronized(deque)
			{
				if(deque.size() <= 0)
					break;
				 key = deque.getLast();
				 deque.removeLast();
				 //keyList.add(key);
			}
			
			if (!key.isValid()) {
				continue;
			}

			// Check what event is available and deal with it
			if (key.isConnectable()) { // finish a connection procedure
				this.finishConnection(key);
			} else if (key.isReadable()) {
				this.read(key);
			} else if (key.isWritable()) {
				this.write(key, this.pendingData);
			}
		}
	}
	
	private void read(SelectionKey key) throws IOException 
	{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();

		// Attempt to read off the channel
		int numRead;
		try {
			numRead = socketChannel.read(this.readBuffer);
//			if (debug == true) 
//			{
//				System.out.println("Read buffer attributes: " + " Capacity: "
//						+ readBuffer.capacity() + "; Limit: "
//						+ readBuffer.limit() + "; Position: "
//						+ readBuffer.position()
//						+ "; user defined the number of read bytes " + numRead);
//			}
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

		// Handle the response
		// try
		// {
		// udpClient.sendUdp(readBuffer);
		// } catch (Exception e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
//		if (debug == true)
//			System.out.println("In the read funtion");
		
	}

	
	// Writes the request body to a buffer.
	// In this case a request body is stored in the "pendingData" object's
	// HashMap instance variable.
	private void write(SelectionKey key, Map pendingData) throws IOException 
	{
		// Debugging printing.
//		if (debug == true)
//			System.out.println("In the write function");
		SocketChannel socketChannel = (SocketChannel) key.channel();

		synchronized (pendingData) 
		{
			// Retrieve the data that must be sent according to its channel.
			List queue = (List) pendingData.get(socketChannel);

			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0); // (ByteBuffer)
															// queue.get(0)
															// returns a
															// ByteBuffer that
															// associated with
															// the specific
															// Socket channel.
//				if (debug == true) {
//					System.out.println("Write to buffer "
//							+ new String(buf.array(), "UTF-8"));
//					System.out.println("Write buffer Attributes: "
//							+ " Capacity: " + buf.capacity() + " Limit: "
//							+ buf.limit() + " Position: " + buf.position());
//				}
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
	
}
