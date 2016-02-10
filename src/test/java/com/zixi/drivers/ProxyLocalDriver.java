package com.zixi.drivers;

import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

import com.zixi.httpserver.*;
import com.zixi.nio.*;
import com.zixi.tcpserver.ZTcpServer;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ProxyLocalDriver extends BroadcasterLoggableApiWorker
implements TestDriver{
	
	//private ApiWorkir adaptiveGroupCreator = new ApiWorkir();
	private FLV flvload1;
	private MyHandler myHandler;
	private String url;
	private ZTcpServer zTcpServer;

	private String function;
	private String source;
	private String mode;
	private String proxy_port;
	
	
	// http://localproxy.zixi.com:4500/channel.flv?url=zixi%3A%2F%2F10.7.0.157%2Ftest&mode=3
	// http://localproxy.zixi.com:proxy_port/function?url=zixi%3A%2F%2Fsource%2Fstream_name&mode=3
	
	public String getUrl() {
		return url;
	}

	public String testIMPL(String function, String source, String stream_name,
			String mode, String proxy_port, String regime) { 
		this.function = function;
		this.source = source;
		this.mode =  mode;
		this.proxy_port = proxy_port;
		
		try 
		{
			flvload1 =  new FLV(InetAddress.getByName("127.0.0.1"), Integer.parseInt(proxy_port));
			Thread t = new Thread(flvload1);
			t.start();	
			zTcpServer = new ZTcpServer(flvload1 , this);
			url = createURL(function,  source,  stream_name, mode,  proxy_port);		
			zTcpServer.runServer(regime);
			t.join();
			
		} catch ( IOException | InterruptedException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return null;
	}
	
	public String getSource() {
		return source;
	}

	public String getMode() {
		return mode;
	}

	public String getProxy_port() {
		return proxy_port;
	}

	public String getFunction() {
		return function;
	}

	// http://localproxy.zixi.com:4500/channel.flv?url=zixi%3A%2F%2F10.7.0.157%2Ftest&mode=3
	// http://localproxy.zixi.xom:proxy_port/function?url=zixi%3A%2F%2Fsource%2Fstream_name&mode=3
	private String createURL(String function, String source, String stream_name,
			String mode, String proxy_port)
	{
		String url = "GET "
				+ "/" + function + "?url=zixi%3A%2F%2F" + source +"%2F"+ stream_name +"&mode="+ mode
				+ " HTTP/1.1\r\nUser-Agent: Mozilla/5.0\r\nHost:127.0.0.1" + ":" + proxy_port
				+ "\r\nAccept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\nConnection: keep-alive\r\n\r\n";
		//return "http://localproxy.zixi.com:" + proxy_port + "/" + function + "?url=zixi%3A%2F%2F" + source +"%2F"+ stream_name +"&mode="+ mode ;
		return url ;
	}
}
 