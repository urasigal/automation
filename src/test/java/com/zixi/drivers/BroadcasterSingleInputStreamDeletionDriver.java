package com.zixi.drivers;

import org.testng.Assert;

import com.zixi.tools.BroadcasterLoggable;
import com.zixi.tools.RemoveInputHelper;

public class BroadcasterSingleInputStreamDeletionDriver extends BroadcasterLoggable implements TestDriver{
	
	RemoveInputHelper removeInputHelper = new RemoveInputHelper();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/zixi/remove_stream.json?";
	
	public String removeInput(String login_ip, String userName, String userPassword, String streamId, String uiport) 
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		return removeInputHelper.sendGet(HTTP + login_ip + params1 + uiport +  params7 + "id" + params4 + streamId, login_ip, responseCookieContainer);
	}
}
