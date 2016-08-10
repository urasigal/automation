package com.zixi.drivers;

import static com.zixi.globals.Macros.ADD_TRANSCODER_PROFILE;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterTranscoderDeletProfileDriver extends
BroadcasterLoggableApiWorker implements TestDriver
{

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String profile_name) throws Exception {
		
		int pid = -8;

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		String outJson = apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/h264_profiles.json" , "", 77, responseCookieContainer, login_ip, this, uiport);

		JSONObject responseJson = new JSONObject(outJson.toString());
		JSONArray outputStreamsArray = responseJson.getJSONArray("profiles");

		String streamName = null;
		for (int i = 0; i < outputStreamsArray.length(); i++) {
			//System.out.println("before");
			JSONObject outputStream = outputStreamsArray.getJSONObject(i);
		   
		    //System.out.println("after");
		    //id1 = stream.getString("id");
		    streamName = outputStream.getString("profile_name");
		    if(streamName.equals(profile_name))
		    {
		    	pid = outputStream.getInt("id");
		    	//testID = profileID;
		    }
		  }
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/del_h264_profile.json?id=" + pid
		, "", ADD_TRANSCODER_PROFILE, responseCookieContainer, login_ip, this, uiport);
	}
}
