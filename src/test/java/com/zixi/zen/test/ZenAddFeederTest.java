package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddFeederDriver;
import com.zixi.drivers.tools.DriverReslut;
import com.zixi.testing.BaseTestZixiMainComponents;

// The goal of the test is to verify an ability of a feeder server to accept a private key file through an UI API request.
public class ZenAddFeederTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddFeederDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "feederName", "feederUser", "feederPass", "shhKeyName", "accessTagName", "testid"})
	@Test
	public void addFeederToZen(String userName, String userPass, String login_ip, String uiport, String feederName, String feederUser, String feederPass, String shhKeyName, String accessTagName, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "feederName", "feederUser", "feederPass", "shhKeyName", "accessTagName", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, feederName, feederUser, feederPass, shhKeyName, accessTagName, testid });
		driverReslut = ((ZenAddFeederDriver) testDriver).addFeeder(userName, userPass, login_ip, uiport, feederName, feederUser, feederPass, shhKeyName, accessTagName);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}