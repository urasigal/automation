package com.zixi.drivers;

import static com.zixi.globals.Macros.UDPMODE;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.zixi.tools.BroadcasterLoggable;
import com.zixi.tools.RemoveInputHelper;
import com.zixi.tools.ApiWorkir;

public class BroadcaserSingleOutputStreamDeletionDriver extends BroadcasterLoggable implements TestDriver{
	
	private ApiWorkir streamDeletor = new ApiWorkir();
	
	public  ArrayList<String> list = new ArrayList<String>();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/zixi/remove_output.json?";
	private String id1 = null;

	final private String rid = "id";
	
	public String testIMPL(String login_ip,String userName,String userPassword,String name,String uiport)
	{
		String response;
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		response = streamDeletor.sendGet(HTTP + login_ip + ":" + uiport + "/zixi/outputs.json", "", 77, responseCookieContainer, login_ip, this );
		JSONObject responseJson = new JSONObject(response.toString());
		JSONArray outputStreamsArray = responseJson.getJSONArray("outputs");

		String streamName = null;
		for (int i = 0; i < outputStreamsArray.length(); i++) {
			//System.out.println("before");
			JSONObject outputStream = outputStreamsArray.getJSONObject(i);
		   
		    //System.out.println("after");
		    //id1 = stream.getString("id");
		    streamName = outputStream.getString("name");
		    if(streamName.equals(name))
		    {
		    	id1 = outputStream.getString("id");
		    }
		  }
		return streamDeletor.sendGet(HTTP + login_ip + ":" + uiport +  params7 + rid + params4 + id1 , streamName, UDPMODE, responseCookieContainer, login_ip, this);
	}
	
	public String getId1() {
		return id1;
	}
}
