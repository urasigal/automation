package com.zixi.drivers;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

import static com.zixi.globals.Macros.*;

public class BroadcasterAdaptiveGroupDeletionDriver extends BroadcasterLoggableApiWorker
		implements TestDriver {

	private ApiWorkir adaptiveGroupCreator = new ApiWorkir();

	private TestParameters testParameters;

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String name) throws Exception {
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/remove_adaptive_channel.json?name=" + name, "", UDPMODE,
				responseCookieContainer, login_ip, this, uiport);
	}
}
