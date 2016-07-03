package com.zixi.drivers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterFileUploadDriver extends BroadcasterLoggableApiWorker implements TestDriver
{

	private BufferedReader in;
	private PrintWriter out;
	
	public String testIMPL(String destinationServerIP, String destinationServerPort, String file_upload_mode) throws IOException { 
		 
		 System.out.println("Connecting to " + destinationServerIP +" on port " + destinationServerPort);
		 Socket client = new Socket(destinationServerIP, Integer.parseInt(destinationServerPort) );
		 client.setSoTimeout(0);
	     System.out.println("Just connected to " + client.getRemoteSocketAddress());
	     InputStream inFromServer = client.getInputStream();
	     OutputStream outToServer = client.getOutputStream();
	     
	     out = new PrintWriter(outToServer, true);
	     
	     out.println(file_upload_mode);
	     
	     in = new BufferedReader(new InputStreamReader(inFromServer));
	     
	     String fromServer = in.readLine();
	     client.close();
	     return fromServer;
	}
}
