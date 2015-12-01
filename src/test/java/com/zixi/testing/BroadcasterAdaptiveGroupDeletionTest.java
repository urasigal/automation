package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterAdaptiveGroupDeletionTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterAdaptiveGroupDeletionDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name",
			 "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass,
			String login_ip, String uiport, String name, String testid)
			throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(
				((BroadcasterAdaptiveGroupDeletionDriver) testDriver).testIMPL(
						userName, userPass, login_ip, uiport, name), "{\"success\":1}");
	}

}
