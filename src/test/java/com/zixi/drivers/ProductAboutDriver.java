package com.zixi.drivers;

import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.JsonParser;

public class ProductAboutDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	public  String getBroadcasterVersion(String login_ip, String uiport, String userName, String userPassword) throws Exception
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm",
																		userName , userPassword, login_ip, uiport);
		
		return JsonParser.getBroadcasterVersion(apiworker.sendGet("http://" + login_ip + ":" + uiport + "/json_about" , "", 
				77, responseCookieContainer, login_ip, this, uiport));
	}
	
//	public static void main(String [] args)
//	{
//		ProductAboutDriver tester = new ProductAboutDriver();
//		System.out.println(tester.getBroadcasterVersion("10.7.0.91", "4444", "admin", "1234"));
//	}
}
