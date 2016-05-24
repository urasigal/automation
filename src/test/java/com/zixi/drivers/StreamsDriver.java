package com.zixi.drivers;

import java.util.function.Supplier;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.JsonParser;

public class StreamsDriver extends
BroadcasterLoggableApiWorker implements TestDriver{
	
	private ApiWorkir apiWorker = new ApiWorkir();
	
	public String getInputStreamIdByName(String name, String loin_ip, String uiport, String userName, String  userPass)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://"
				+ loin_ip + ":" + uiport + "/login.htm", userName, userPass,
				loin_ip, uiport);
		
		return JsonParser.receiverInputGetStreamIdByname(apiWorker.sendGet("http://" + loin_ip +":"+ uiport + "/in_streams.json?complete=1", "", 77, 
				responseCookieContainer, loin_ip, this, uiport), name);
	}
	
	public String getOutputStreamIdByName(String name, String loin_ip, String uiport, String userName, String  userPass)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://"
				+ loin_ip + ":" + uiport + "/login.htm", userName, userPass,
				loin_ip, uiport);
		
		return JsonParser.receiverOutputGetStreamIdByname(apiWorker.sendGet("http://" + loin_ip +":"+ uiport + "/out_streams.json", "", 77, 
				responseCookieContainer, loin_ip, this, uiport), name);
	}
	
	public static int getTranscoderProfiles(Supplier<String> supplier, String profile_name)
	{
		return JsonParser.getTranscoderProfiles(supplier.get(), profile_name);
	}
	
	public int getInputStreamBitrate(String name, String loin_ip, String uiport, String userName, String  userPass)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://"
				+ loin_ip + ":" + uiport + "/login.htm", userName, userPass,
				loin_ip, uiport);
		
		return JsonParser.getInputStreamBitrate(apiWorker.sendGet("http://" + loin_ip +":"+ uiport + "/input_stream_stats.json?id=" + name, "", 77, 
				responseCookieContainer, loin_ip, this, uiport), name);
	}
	
	// This function is using to get the source of a receiver's input stream.
	public String getIpAddressOfActiveInputStreamOnReceiver(String name, String loin_ip, String uiport, String userName, String  userPass)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://"
				+ loin_ip + ":" + uiport + "/login.htm", userName, userPass,
				loin_ip, uiport);
		
		return JsonParser.getActiveIpOfReceiverInput( () -> apiWorker.sendGet("http://" + loin_ip +":"+ uiport + "/in_streams.json?complete=1", "", 77, 
				responseCookieContainer, loin_ip, this, uiport), name);
	}
}
