package com.zixi.drivers;

import static com.zixi.globals.Macros.ADD_TRANSCODER_PROFILE;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class DeleteAudioProfileDriver extends BroadcasterLoggableApiWorker implements TestDriver
{

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String profile_name, String testid) throws Exception {
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		int audioProfile = StreamsDriver.getTranscoderProfiles(() -> apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/aac_profiles.json" , "", 77, responseCookieContainer, login_ip, this, uiport),  profile_name);
		
		// Transcode a stream zhopa
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/del_aac_profile.json?id=" + audioProfile
		, "", ADD_TRANSCODER_PROFILE, responseCookieContainer, login_ip, this, uiport);
		
	}

}
