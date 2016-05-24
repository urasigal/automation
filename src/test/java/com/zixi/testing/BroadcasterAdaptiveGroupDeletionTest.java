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
		pid = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
				testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name",
						 "testid" }, 
						
						new String[] { userName, userPass, login_ip, uiport, name,
						 testid });
		
		Assert.assertEquals(
				((BroadcasterAdaptiveGroupDeletionDriver) testDriver).testIMPL(
						userName, userPass, login_ip, uiport, name), "{\"success\":1}");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(pid, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}

}
