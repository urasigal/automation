package com.zixi.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

import com.zixi.drivers.ProxyLocalDriver;
import com.zixi.httpserver.MyHandler;
import com.zixi.nio.FLV;

public class ZServerThread extends Thread{
	
	 private MyHandler newMyHandler;
	 private FLV flvload1;
	 private Socket socket = null;
	 private ProxyLocalDriver proxyLocalDriver;
	 private String regime;
    public ZServerThread(Socket socket, FLV flvload12, ProxyLocalDriver proxyLocalDriver, String regime) 
    {
        super("ZServerThread");
        this.proxyLocalDriver = proxyLocalDriver;
        this.flvload1 = flvload12;
        this.socket = socket;
        this.regime = regime;
        try {
			socket.setSoTimeout(1000000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void run() {
	    try {
	    	newMyHandler = new MyHandler(flvload1);
	    	newMyHandler.setSroxyLocalDriver(proxyLocalDriver);
	    	newMyHandler.handle(socket,regime);
		} catch (IOException e) {
			
			
			System.out.println("The error is: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
