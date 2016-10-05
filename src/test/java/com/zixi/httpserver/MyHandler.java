package com.zixi.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.zixi.drivers.drivers.ProxyLocalDriver;
import com.zixi.nio.FLV;
import com.zixi.nio.RspHandler;

public class MyHandler {
	 
	private String function;
	private String source;
	private String mode;
	private String proxy_port;
	private Map<InputStream, OutputStream> oldConnetions = new  HashMap<>();
	
	 private FLV flvload1;
	 
	 private ProxyLocalDriver sroxyLocalDriver;
	 
	 private Map<MyHandler, SocketChannel> handlerToSocket;

	public MyHandler(FLV flvload1)
	 {
		 this.flvload1 = flvload1;
		 //flvload1.setMyHandler(this);
	 }
	 
	  public void setSroxyLocalDriver(ProxyLocalDriver sroxyLocalDriver) 
	  {
		  this.sroxyLocalDriver = sroxyLocalDriver;
	  }
	
	
	public void handle(Socket socket, String regime) throws IOException
	{
		function   = sroxyLocalDriver.getFunction();
		source     = sroxyLocalDriver.getSource();
		mode       = sroxyLocalDriver.getMode();
		proxy_port = sroxyLocalDriver.getProxy_port(); 
		int connectionErrors = 0;
		
		InputStream in = socket.getInputStream();
    	OutputStream out = socket.getOutputStream();
    	oldConnetions.put(in,out);
		StringBuilder sb = new StringBuilder();
		String inputLine;
        
		Queue<byte[]> queue = new LinkedList<byte[]>();
		
	      try
	      {
	    	  int n;
	    	  byte[] b = new byte[9000];
	    	  while(in.read(b)!=-1)
	    	  {
	    		  // Debug printing
	    		  String requestFromClient = new String(b);
	    		  
	    		  String streamName  = StringUtils.substringBetween(requestFromClient, "/", ".flv");
	    		  System.out.println("Client request " + requestFromClient);
	    		  System.out.println("Requsted stream name " + streamName); 
	    		  synchronized(flvload1)
	    		  {
	    			  String URL = "";
	    			  if (regime.equals("HLS"))
	    			  {
	    				  streamName = StringUtils.substringBetween(requestFromClient, "?bbb=" , ".m3u8");
	    				  System.out.println("Requsted stream name " + streamName); 
	    				  URL  = "GET /"
	    							+ streamName + ".m3u8 HTTP/1.1" + "\r\nUser-Agent: Mozilla/5.0\r\nHost:127.0.0.1" + ":" + proxy_port
	    							+ "\r\nAccept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\nConnection: keep-alive\r\n\r\n";	
	    			  }
	    			  else
	    			  {
				  		URL  = "GET "
						+ "/" + function + "?url=zixi%3A%2F%2F" + source +"%2F"+ streamName +"&mode="+ mode
						+ " HTTP/1.1\r\nUser-Agent: Mozilla/5.0\r\nHost:127.0.0.1" + ":" + proxy_port
						+ "\r\nAccept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\nConnection: keep-alive\r\n\r\n";	
	    			  }
			  		flvload1.send(URL.getBytes(), queue, this);

	    		  }
	    		  
	    		  while (true) 
			      { 
			    	  synchronized(queue)
				      {
				    	  if(queue.isEmpty())
				    	  {
				    		  queue.wait();
				    	  }
				    	  if (!queue.isEmpty())
				    		  try{
				    		  out.write(queue.poll()); ///////////////////
				    		  }catch (IOException e)
					    	  {
				    			  connectionErrors ++;
				    			  System.out.println("Error is " + e.getMessage());
				    			  if(connectionErrors > 100)
				    			  {
				    				  SocketChannel channelToBeClosed =  handlerToSocket.get(this);
				    				  channelToBeClosed.close();
				    				  throw new IOException();
				    			  }
					    	  }
			    	  }
			      } 
	    	  }
	      } catch (InterruptedException e) {
			// TODO Auto-generated catch block
	    	  System.out.println("Error is " + e.getMessage());
			e.printStackTrace();
		}
	      
	      finally
	      {
	    	  out.close();
	      }
	  }
	
	 public void setHandlerToSocket(Map<MyHandler, SocketChannel> handlerToSocket) {
			this.handlerToSocket = handlerToSocket;
		}
	}