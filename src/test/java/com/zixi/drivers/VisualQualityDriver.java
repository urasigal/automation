package com.zixi.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.Reporter;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class VisualQualityDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	private static final String hostName   = "10.7.0.111";
	private static final int    portNumber = 4445;
	private static final int    attempts   = 20;
	private static final int    hlsAttempts   = 200;
	private static final String fromUser = "9999";
	private static final int    negativeAttempts   = 5;
	private static final String HLS = "hls";
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int loopCnt;
	
	public String testVideo() 
	{
		// TODO Auto-generated method stub
		long sum = 0;
		loopCnt = 0;
		String resultToTest = null;
		
		try {

		clientSocket = new Socket(hostName, portNumber);
		clientSocket.setSoTimeout(0);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String fromServer = in.readLine();
		System.out.println("Expected is connected " + fromServer);
		
		if (fromServer.equals("connected"))
		{
			out.println(fromUser);
			fromServer = in.readLine();
			System.out.println("Expected is output " + fromServer);
			if(fromServer.equals("output"))
			{
				fromServer = in.readLine();
				String segments[] = fromServer.split("@");
				
				if(segments.length > 1)		
					if(segments[0].equals("pass") || segments[0].equals("fail"))
					{
						resultToTest = fromServer;
						out.println("Bye");   
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
		return resultToTest;
	}
}
