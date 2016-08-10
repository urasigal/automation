package com.zixi.drivers;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

import static com.zixi.globals.Macros.*;

public class FeederUdpOutToFfmpegDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	private ApiWorkir streamDeletor = new ApiWorkir();

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String name, String mip, String port, String ip,
			String prog, String chan, String oh, String op, String onic,
			String ottl, String osmooth) throws Exception {
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/set_udp_out?name=" + name + "&mip=" + mip + "&port=" + port + "&ip=" + ip + "&prog=" + prog + "&chan=" + chan +
				"&oh=" + oh + "&op=" + op + "&onic=" + onic + "&ottl=" + ottl + "&osmooth=" + osmooth ,"", UDPMODE,
				responseCookieContainer, login_ip, this, uiport);
	}

}
