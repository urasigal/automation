package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederPostKeyDriver;

// The goal of the test is to verify an ability of a feeder server to accept a private key file through an UI API request.
public class FeederPostKeyTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {

		// Load the page in the browser
		testDriver = new FeederPostKeyDriver();
	}

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "testid"})
	@Test
	public void uploadPrivateKeyToFeeder(String userName, String userPass, String login_ip, String uiport, String testid) throws Exception {
		
		this.testid = testid;
		
		// Provide parameters to a TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, testid });
		
		Assert.assertEquals(( (FeederPostKeyDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport), "1");
	}
}
