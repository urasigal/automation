package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class AddAudioProfileDriver extends BroadcasterLoggableApiWorker implements TestDriver
{
	// This class used in order to perfom the broadcaster's WEB API function.
	private ApiWorkir streamCreator = new ApiWorkir();
	
	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String profile_name, String enc, String bitrate,
			String profile, String testid) {
		
		testParameters = new TestParameters("userName:"+ userName, "userPass:"+ userPass, "login_ip:"+ login_ip, "uiport" + uiport, "profile_name" + profile_name +
											"enc" + enc, "bitrate" + bitrate, "profile" + profile, "testid" + testid);
		
		// Extract session credentials parameters.
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		
		return streamCreator.sendGet("http://" + login_ip + ":" + 4444 + "/zixi/add_aac_profile.json?profile_name=" + profile_name + "&enc=" + enc +
				 "&bitrate=" + bitrate + "&profile=" + profile, "", ADD_TRANSCODER_PROFILE, responseCookieContainer, login_ip, this, uiport);
		
	}
	
}
