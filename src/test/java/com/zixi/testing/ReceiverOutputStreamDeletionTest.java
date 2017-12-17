package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverOutputStreamDeletionDriver;

public class ReceiverOutputStreamDeletionTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {testDriver = new ReceiverOutputStreamDeletionDriver();}

	@Parameters({ "login_ip", "userName", "userPass", "uiport" ,"stream_name","destination","testid"})
	@Test		
	public void receiverDeleteOutputStreamTest(String login_ip, String userName, String userPass, String uiport, String stream_name, String  destination, String testid) 
	throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "login_ip", "userName", "userPass", "uiport" ,"stream_name","destination","testid" }, 	
		new String[] { login_ip, userName, userPass,uiport ,stream_name,destination,testid});
		
		driverReslut = ((ReceiverOutputStreamDeletionDriver) testDriver).testIMPL(  login_ip,  userName,  userPass,  uiport , stream_name, testid);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream '" + destination + "' removed.");
	}
}
