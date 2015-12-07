package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterTranscoderDeletProfileTest extends BaseTest{

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterTranscoderDeletProfileDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "profile_name",
			"testid" })
	@Test
	public void broadcasterDeleteProfile(String userName,
			String userPass, String login_ip, String uiport, String profile_name, String testid)
			throws InterruptedException {

		this.testid = testid;
		Assert.assertEquals(((BroadcasterTranscoderDeletProfileDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, profile_name),
				"Profile deleted");
	}
	
}
