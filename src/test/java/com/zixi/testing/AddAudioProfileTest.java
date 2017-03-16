package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.AddAudioProfileDriver;

public class AddAudioProfileTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new AddAudioProfileDriver();
	}

	@Parameters({ 
		"userName",
		"userPass", 
		"login_ip", 
		"uiport",
		"profile_name",
		"enc",
		"bitrate",
		"profile",
		"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String login_ip, String uiport, String profile_name, String enc, 
			String bitrate, String profile , String testid)
			throws Exception {
		// Set the "testid" parameter to an "extended" class property.
		this.testid = testid; 
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testLinktestParameters = buildTestParametersString(new String[] {"userName","userPass", "login_ip", "uiport","profile_name","enc","bitrate","profile",
				"testid"}, new String[] { userName, userPass, login_ip, uiport, profile_name, enc, bitrate, profile, testid});
		
		Assert.assertEquals(((AddAudioProfileDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, profile_name, enc, bitrate, profile, testid), 
				"Profile added, existing transcoded streams may be restarted.");
	}
}
