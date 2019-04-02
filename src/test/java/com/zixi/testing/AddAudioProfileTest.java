package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.AddAudioProfileDriver;

public class AddAudioProfileTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		testDriver = new AddAudioProfileDriver();
	}
   // http://10.7.0.47:4444/zixi/add_aac_profile.json?profile_name=audio_profile_name&enc=1&bitrate=6000&profile=0 
	@Parameters({  "userName", "userPass",  "login_ip", "uiport", "profile_name", "enc", "bitrate", "profile",
	"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String login_ip, String uiport, String profile_name, String enc, 
			String bitrate, String profile , String testid)
			throws Exception {
		// Set the "testid" parameter to an "extended" class property.
		this.testid = testid; 
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] {"userName","userPass", "login_ip", "uiport","profile_name","enc","bitrate","profile",
		"testid"}, new String[] { userName, userPass, login_ip, uiport, profile_name, enc, bitrate, profile, testid});
		
		Assert.assertEquals(((AddAudioProfileDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, profile_name, enc, bitrate, profile, testid), 
				"Profile added, existing transcoded streams may be restarted.");
	}
}
