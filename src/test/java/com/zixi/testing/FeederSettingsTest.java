package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederSettingsDriver;

public class FeederSettingsTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {

		// Load the page in the browser
		testDriver = new FeederSettingsDriver();
	}
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "host", "ssh_port", "ssh_user", "testid"})
	@Test
	public void uploadPrivateKeyToFeeder(String userName, String userPass, String login_ip, String uiport, String host,
			String ssh_port, String ssh_user, String testid) throws Exception {
		
		this.testid = testid;
		
		// Provide parameters to a TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "host", "ssh_port", "ssh_user", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, host, ssh_port, ssh_user, testid });
		
		Assert.assertEquals(( (FeederSettingsDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, host, ssh_port, ssh_user), host);
	}

}
