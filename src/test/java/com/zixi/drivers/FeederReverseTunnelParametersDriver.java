package com.zixi.drivers;

import static com.zixi.globals.Macros.FEEDER_SSH_SERVER_STATUS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.zixi.globals.Macros.*; 

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederReverseTunnelParametersDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	 
	public String testIMPL(String userName, String userPass, String login_ip, String uiport, String on, String target_port, String target_ip, String remote_port) throws Exception  
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/add_ssh_tunnel.json?on=" + on + "&target_port=" + target_port + "&target_ip=" + target_ip + 
				"&remote_port=" + remote_port, "", UDPMODE, responseCookieContainer, login_ip, this, uiport);
	}

}
