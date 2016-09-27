package com.zixi.drivers;

import org.testng.Assert;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.RemoveInputHelper;

public class BroadcasterSingleInputStreamDeletionDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	RemoveInputHelper removeInputHelper = new RemoveInputHelper();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params7 = "/zixi/remove_stream.json?";
	
	public String removeInput(String login_ip, String userName, String userPassword, String streamId, String uiport) throws Exception 
	{
		testParameters 			= new TestParameters("login_ip:"+login_ip, "userName:" + userName , "userPassword:" + userPassword, "streamId:" + streamId, "uiport:" + uiport);
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		return removeInputHelper.sendGet(HTTP + login_ip + params1 + uiport +  params7 + "id" + params4 + streamId, login_ip, responseCookieContainer);
	}
}
