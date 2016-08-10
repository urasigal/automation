package com.zixi.drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederPostKeyDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	FileInputStream fileInputStream=null;
	File sppk = null;
	 
	public String testIMPL(String userName, String userPass, String login_ip, String uiport) throws Exception  
	{
		// Post request content.
		sppk  = new File("src/main/resources/sppk"); // Open a file which contains a RCA key.
		byte[] keyByteArray = new byte[(int) sppk.length()];
		
		fileInputStream = new FileInputStream(sppk);
	    fileInputStream.read(keyByteArray);
	    fileInputStream.close();
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		apiworker.inserKeyToSpecificFeeder("http://" + login_ip + ":" + uiport + "/upload_ssh_key.htm", "", responseCookieContainer, login_ip, this, uiport, keyByteArray);
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/get_ssh_status.json", "", FEEDER_SSH_KEY_STATUS, responseCookieContainer, login_ip, this, uiport);
	}
}
