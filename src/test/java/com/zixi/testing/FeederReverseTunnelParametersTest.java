package com.zixi.testing;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.zixi.drivers.*;

public class FeederReverseTunnelParametersTest extends BaseTest{
	@BeforeClass
	public void testInit() {

		// Load the page in the browser
		testDriver = new FeederReverseTunnelParametersDriver();
	}
	
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "on", "target_port", "target_ip", "remote_port", "testid"})
	@Test
	public void uploadPrivateKeyToFeeder( String userName, String userPass, String login_ip, String uiport, String on, 
			String target_port, String target_ip, String remote_port, String testid) throws Exception {
		
		this.testid = testid;
		
		// Provide parameters to a TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "on", "target_port", "target_ip", "remote_port", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, on, target_port, target_ip, remote_port, testid });
		
		Assert.assertEquals(((FeederReverseTunnelParametersDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, on, target_port, target_ip, remote_port), "Tunnel added.");
	}
}
