package com.zixi.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.testng.Reporter;
import com.zixi.drivers.drivers.AutomatedTelnetClient;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FFMPEGImageStatisticTestDriver extends BroadcasterLoggableApiWorker implements TestDriver {

	private static final String hostName           =	"10.7.0.150";
	private static final int    portNumber         =	4445;
	private static final int    attempts           = 	10;
	private static final int    hlsAttempts   	   = 	200;
	private static final String fromUser      	   = 	"get";
	private static final int    negativeAttempts   = 	5;
	private static final String HLS                = 	"hls";
	private Socket 				clientSocket;
	private PrintWriter 		out;
	private BufferedReader 		in;
	private int 				loopCnt;
	
	// Default constructor.
	public FFMPEGImageStatisticTestDriver() {}
	// Parameterized constructor.
	public FFMPEGImageStatisticTestDriver(StringBuffer testFlowDescriptor) { super(testFlowDescriptor); }
	
	public String testStatistic() 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		try {
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < attempts; loopCnt ++ )
				{
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted " + fromServer);
					}
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
	
	public String testStatistic(String mode) 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		try {
			// This class implements client sockets (also called just "sockets"). A socket is an endpoint for communication between two machines.
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
            out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < hlsAttempts; loopCnt ++ )
				{
					out.println(mode);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number, actual is: " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted, actual is: " + fromServer);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
	
	public String testStatistic(boolean isNegative) 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		try {

			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
             out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < negativeAttempts; loopCnt ++ )
				{
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number, actual is: " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted, actual is: " + fromServer);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
		}
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (sum == 0 )
		{
			return "good";
		}
		else 
			return "bad";
	}

	public String backUpBondedPartialLimitation()
	{
		/////////////////////////////////////THREAD DEFNINITION AND THREAD STARTING///////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * In order to provide functioning of this test the following preset must be on the cisco's router.
		 * 
		 */
		Thread ciscoTelnetThread = new Thread( () -> {
			try {
	    		AutomatedTelnetClient telnet = new AutomatedTelnetClient("10.7.0.140", "cisco", "cisco");
	    		
	    		telnet.sendCommand("configure terminal\n");
	    		telnet.sendCommand("interface gigabitEthernet 1/0\n");
	    		telnet.sendCommand("interface gigabitEthernet 1/0\n");
	    		telnet.sendCommand("no traffic-shape group 145 128000 7936 7936 1000\n");
	    		telnet.sendCommand("no traffic-shape group 145 128000 7936 7936 1000\n");
	    		
	    		while(! Thread.interrupted()){
		    		System.out.println("Got Connection...");
		    		
		    		Thread.sleep(180_000); // Wait a two minute.
		    		
		    		telnet.sendCommand("traffic-shape group 145 128000 7936 7936 1000\n");
		    		telnet.sendCommand("traffic-shape group 145 128000 7936 7936 1000\n");
		    		
		    		Thread.sleep(180_000); // Wait a two minute.
		    		
		    		telnet.sendCommand("no traffic-shape group 145 128000 7936 7936 1000\n");
		    		telnet.sendCommand("no traffic-shape group 145 128000 7936 7936 1000\n");
			} // End while
	    		telnet.disconnect();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		}
		); ciscoTelnetThread.start(); // run the thread
		////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////		
		
		long sum = 0;
		loopCnt = 0;
		try {
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
		 
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			if (fromServer.equals("connected"))
			{
				for (loopCnt = 0; loopCnt < 60_000; loopCnt ++ )
				{
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted " + fromServer);
					}
					Thread.sleep(10_000); 
				}
			}
			ciscoTelnetThread.interrupt();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
	
	public String backUpBondedFullLimitation()
	{
		Thread mainThread = Thread.currentThread();
		testFlowDescriptor.append("\nStart the test driver FFMPEGImageStatisticTestDriver.backUpBondedFullLimitation" );
		/////////////////////////////////////THREAD DEFNINITION AND THREAD STARTING...////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 * In order to provide correct functioning of this test the following preset must be done on the cisco router.
		 * 
		 */
		Thread ciscoTelnetThread = new Thread( () -> {
			try {
	    		AutomatedTelnetClient telnet = new AutomatedTelnetClient("10.7.0.140", "cisco", "cisco");
	    		
	    		telnet.sendCommand("configure terminal\n");
	    		telnet.sendCommand("interface gigabitEthernet 1/0\n");
	    		telnet.sendCommand("interface gigabitEthernet 1/0\n");
	    		telnet.sendCommand("no shutdown\n");
	    		telnet.sendCommand("no shutdown\n");
	    		
	    		while(! Thread.interrupted()){
		    		System.out.println("Got Connection...");
		    		
		    		Thread.sleep(180_000); // Wait a three minutes.
		    		
		    		telnet.sendCommand("shutdown\n");
		    		telnet.sendCommand("shutdown\n");
		    		
		    		Thread.sleep(180_000); // Wait a three minutes.
		    		
		    		telnet.sendCommand("no shutdown\n");
		    		telnet.sendCommand("no shutdown\n");
			} // End while
	    		telnet.sendCommand("no shutdown\n");
	    		telnet.sendCommand("no shutdown\n");
	    		telnet.disconnect();
	    	
			} catch (Exception e) {
	    		mainThread.interrupt();
	    		e.printStackTrace();
	    	}
		}); 
		////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////		
		ciscoTelnetThread.start(); // run the thread
		
		long sum = 0;
		loopCnt  = 0;
		try {
			clientSocket = new Socket(hostName, portNumber);
			clientSocket.setSoTimeout(120000);
			 
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
			String fromServer = in.readLine();
			System.out.println("Expected is connected " + fromServer);
			
			if (fromServer.equals("connected"))
			{
				// Ask FFMPEG server to bring its results.
				for (loopCnt = 0; loopCnt < 20; loopCnt ++ )
				{
					if (mainThread.isInterrupted())
					{
						return "bad";
					}
					
					out.println(fromUser);
					fromServer = in.readLine();
					System.out.println("Expected is output " + fromServer);
					if(fromServer.equals("output"))
					{
						fromServer = in.readLine();
						System.out.println("Expected is number " + fromServer);
						if(fromServer.equals("1"))
						{
							sum += Long.parseLong(fromServer, 10);   
						}
						fromServer = in.readLine();
						System.out.println("Expected is accepted " + fromServer);
					}
					// Wait because of 
					//Thread.sleep(10_000); 
				}
			}
			
			
			ciscoTelnetThread.interrupt();
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
		} 
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		finally{
			try {
				Reporter.log("FFMPEG success measurement relation: " + sum+ " / " + loopCnt);
			if (clientSocket != null)
				clientSocket.close();
			if (out != null)
				out.close();
			if (in!=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if ((sum / attempts) >= 0.9)
		{
			return "good";
		}
		else 
			return "bad";
	}
}
