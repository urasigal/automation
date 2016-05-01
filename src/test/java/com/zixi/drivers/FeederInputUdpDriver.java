package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederInputUdpDriver extends BroadcasterLoggableApiWorker implements
TestDriver{

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String mip, String port, String ip, String name,
			String ssm, String rtp_type) {
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		return apiworker.sendGet("http://" + login_ip  + ":" + uiport + "/add_input?mip="  + mip + "&port=" + port + "&ip=" + ip + "&name=" + 
				name + "&ssm=" + ssm + "&rtp_type=" + rtp_type, "", UDPMODE, responseCookieContainer, login_ip, this, uiport);
	}

}
