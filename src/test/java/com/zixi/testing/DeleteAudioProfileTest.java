package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.DeleteAudioProfileDriver;

public class DeleteAudioProfileTest extends BaseTestZixiMainComponents{
	@BeforeClass
	public void testInit() {
		testDriver = new DeleteAudioProfileDriver();
	}

	@Parameters({ "userName","userPass", "login_ip", "uiport", "profile_name", "testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String login_ip, String uiport, String profile_name, String testid)
	throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] {"userName","userPass", "login_ip", "uiport","profile_name",
		"testid"}, new String[] { userName, userPass, login_ip, uiport, profile_name, testid});
		
		Assert.assertEquals(((DeleteAudioProfileDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, profile_name, testid), 
		"Profile deleted");
	}
}
