package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverOutputStreamDeletionDriver;

public class ReceiverOutputStreamDeletionTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new ReceiverOutputStreamDeletionDriver();
	}

	@Parameters({ "login_ip", "userName", "userPass", "uiport" ,"stream_name","destination","testid"})
	@Test		
	public void receiverDeleteOutputStreamTest(String login_ip, String userName, String userPass, String uiport 
			,String stream_name, String  destination, String testid) throws Exception {
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "login_ip", "userName", "userPass", "uiport" ,"stream_name","destination","testid" }, 
				
				new String[] { login_ip, userName, userPass,uiport ,stream_name,destination,testid});
		
		
		Assert.assertEquals(((ReceiverOutputStreamDeletionDriver) testDriver).testIMPL(
				 login_ip,  userName,  userPass,  uiport 
				, stream_name, testid), "Stream '" + destination + "' removed.");
	}
}
