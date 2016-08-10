package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.JsonParser;

public class InputStreamDetailsDriver extends BroadcasterLoggableApiWorker
implements TestDriver{
	
	private ApiWorkir apiWorker = new ApiWorkir();
	
	private void logIn(String login_ip, String uiport, String userName, String userPass) throws Exception
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" +
				 login_ip + ":" + uiport + "/login.htm", userName, userPass,
				login_ip, uiport);
	}
	
	public String findSourceIpOfInputStream(String id , String login_ip, String uiport, String userName, String userPass) throws Exception
	{
		JsonParser jsonParser  = new JsonParser();
		logIn(login_ip, uiport, userName, userPass);
		return jsonParser.getSourceInputIpById(apiWorker.sendGet("http://" + login_ip + ":" + uiport + GET_ALL_INPUTS_DATA, id,
				77, responseCookieContainer, login_ip, this, uiport), id);
	}
	
	public String findSourceIpOfOutputStream(String id , String login_ip, String uiport, String userName, String userPass) throws Exception
	{
		JsonParser jsonParser  = new JsonParser();
		logIn(login_ip, uiport, userName, userPass);
		return jsonParser.getSourceOutputIpById(apiWorker.sendGet("http://" + login_ip + ":" + uiport + GET_ALL_OUTPUTS_DATA, id,
				77, responseCookieContainer, login_ip, this, uiport), id);
	}
	
	
	// Testing main function, coment it out if no needed - not in the testing mode.
	public static void main(String[] args) throws Exception
	{
		InputStreamDetailsDriver inputStreamDetailsDriver = new InputStreamDetailsDriver();
		System.out.println(inputStreamDetailsDriver.findSourceIpOfInputStream("best", "10.7.0.91", "4444", "admin", "1234")); 
	}
}
