package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterTranscoderDeletProfileDriver;

public class BroadcasterTranscoderDeletProfileTest extends BaseTest{

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterTranscoderDeletProfileDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "profile_name", "testid" })
	@Test
	public void broadcasterDeleteProfile(String userName, String userPass, String login_ip, String uiport, String profile_name, String testid)throws Exception {

		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testLinktestParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "profile_name", "testid" }, 
				
		new String[] {userName,userPass, login_ip, uiport, profile_name,testid });
		
		
		Assert.assertEquals(((BroadcasterTranscoderDeletProfileDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, profile_name),
				"Profile deleted");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
}
