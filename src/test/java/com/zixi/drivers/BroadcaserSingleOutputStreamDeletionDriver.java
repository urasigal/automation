package com.zixi.drivers;

import static com.zixi.globals.Macros.UDPMODE;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.RemoveInputHelper;
import com.zixi.tools.ApiWorkir;


// This test driver is used in purpose of output stream deletion on a broadcaster server.
public class BroadcaserSingleOutputStreamDeletionDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	//private ApiWorkir streamDeletor = new ApiWorkir();
	
	public  ArrayList<String> list = new ArrayList<String>();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	private String internalStreamID = null;
	private String internalStreamName = null;
	final private String rid = "id";
	
	public String testIMPL(String login_ip,String userName,String userPassword,String name,String uiport)
	{
		testParameters = new TestParameters("login_ip:"+login_ip,"userName:"+userName ,"userPassword:"+userPassword ,"name:"+name ,"uiport:"+uiport);
		String response;
		
		// Super class member (BroadcasterLoggableApiWorker) 
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		
		response = apiworker.sendGet(HTTP + login_ip + ":" + uiport + "/zixi/outputs.json", "", 77, responseCookieContainer, login_ip, this, uiport );
		
		JSONObject responseJson = new JSONObject(response.toString());
		JSONArray outputStreamsArray = responseJson.getJSONArray("outputs");

		String streamName = null;
		for (int i = 0; i < outputStreamsArray.length(); i++) {
			//System.out.println("before");
			JSONObject outputStream = outputStreamsArray.getJSONObject(i);
		    streamName = outputStream.getString("name");
		    if(streamName.equals(name))
		    {
		    	internalStreamID = outputStream.getString("id");
		    	internalStreamName = outputStream.getString("stream_id");
		    }
		  }
		return apiworker.sendGet(HTTP + login_ip + ":" + uiport +  "/zixi/remove_output.json?" + rid + "=" + internalStreamID +"&stream=" + internalStreamName , streamName, UDPMODE, responseCookieContainer, login_ip, this, uiport);
	}
	
	public String getId1() {
		return internalStreamName;
	}
}
