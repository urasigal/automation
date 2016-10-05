package com.zixi.tcpserver;

import java.net.*;
import java.io.*;

import com.zixi.drivers.drivers.ProxyLocalDriver;
import com.zixi.httpserver.MyHandler;
import com.zixi.nio.FLV;
 
public class ZTcpServer {
	
    //private MyHandler myHandler;
    
	private FLV flvload1;  
    private ProxyLocalDriver proxyLocalDriver;
	public ZTcpServer(FLV flvload1, ProxyLocalDriver proxyLocalDriver) {  
		this.flvload1 = flvload1;
		this.proxyLocalDriver = proxyLocalDriver;
	}

	public void runServer(String regime) throws IOException  
	{
        int portNumber = 8087;
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) 
        { 
            while (listening) 
            {
            	ZServerThread zServerThread =   new ZServerThread(serverSocket.accept(), flvload1, proxyLocalDriver, regime); // Blocking here.
            	//zServerThread.setMyHandler(myHandler);
            	zServerThread.start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}