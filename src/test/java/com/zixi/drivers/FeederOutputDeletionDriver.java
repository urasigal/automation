package com.zixi.drivers;

import static com.zixi.globals.Macros.PUSHINMODE;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggable;

public class FeederOutputDeletionDriver extends BroadcasterLoggable implements
		TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params7 = "/del_sink?";

	final private static String ruiport = "uiport";
	final private static String rid = "id";
	final private static String rmip = "mip";
	final private static String rport = "port";
	final private static String rip = "ip";
	final private static String rprog = "prog";
	final private static String rchan = "chan";
	final private static String rtype = "type";

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String id, String mip, String port, String ip,
			String prog, String chan, String type, String host) {
		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:"
				+ id, "mip:" + mip, "port:" + port, "ip:" + ip, "prog:" + prog,
				"chan:" + chan, "type:" + type, "host:" + host);
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP
				+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
				login_ip, uiport);

		return streamCreator.sendGet(HTTP + login_ip + params1 + uiport
				+ params7 + rmip + "=" + mip + "&" + rport + "=" + port + "&"
				+ rip + "=" + ip + "&" + rprog + "=" + prog + "&" + rchan + "="
				+ chan + "&" + rtype + "=" + type + "&" + rid + "="
				+ "zixi%3A%2F%2F" + host + "%3A2088%2F" + id, id, PUSHINMODE,
				responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
	}

}
