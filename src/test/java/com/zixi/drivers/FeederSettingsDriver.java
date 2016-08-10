package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederSettingsDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	FileInputStream fileInputStream=null;
	File sppk = null; 
	 
	public String testIMPL(String userName, String userPass, String login_ip, String uiport, String host, String ssh_port, String ssh_user) throws Exception  
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/set_ssh.json?host=" + host + "&ssh_port=" + ssh_port + "&ssh_user=" +  ssh_user, "", 
				FEEDER_SSH_SERVER_STATUS, responseCookieContainer, login_ip, this, uiport);
	}

}
