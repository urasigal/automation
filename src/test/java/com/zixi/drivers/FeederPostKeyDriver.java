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
	 
	public String testIMPL() throws IOException  
	{
		// Post request content.
		sppk  = new File("src/main/resources/sppk"); // Open a file which contains a RCA key.
		byte[] keyByteArray = new byte[(int) sppk.length()];
		
		fileInputStream = new FileInputStream(sppk);
	    fileInputStream.read(keyByteArray);
	    fileInputStream.close();
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + "10.7.0.65" + ":" + 4200 + "/login.htm", "admin" , "1234", "10.7.0.65", "4200");
		apiworker.inserKeyToSpecificFeeder("http://10.7.0.65:4200/upload_ssh_key.htm", "", 77, responseCookieContainer, "10.7.0.65", this, "4200", keyByteArray);
		return apiworker.sendGet("http://10.7.0.65:4200/get_ssh_status.json", "", FEEDER_SSH_KEY_STATUS, responseCookieContainer, "10.7.0.65", this, "4200");
	}
	
	public static void main(String [] args) throws IOException
	{
		new FeederPostKeyDriver().testIMPL();
	}
}
