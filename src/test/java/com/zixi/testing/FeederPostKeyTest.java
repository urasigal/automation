package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederPostKeyDriver;

// The goal of the test is to verify an ability of a feeder server to accept a private key file through an UI API request.
public class FeederPostKeyTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederPostKeyDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "testid"})
	@Test
	public void uploadPrivateKeyFeeder(String userName, String userPass, String login_ip, String uiport, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, testid });
		
		driverReslut = ((FeederPostKeyDriver) testDriver).uploadPrivateKeyFeeder( userName, userPass, login_ip, uiport);
		Assert.assertTrue(driverReslut.getResult().contains("SSL Certificate is uploaded successfully"));
	}
	
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "testid"})
	@Test
	public void uploadSslCertificateToBroadcasterServer(String userName, String userPass, String login_ip, String uiport, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, testid });
		
		driverReslut = ((FeederPostKeyDriver) testDriver).uploadHttpsCertificate(userName, userPass, login_ip, uiport,"src/main/resources/sppk", "WebKitFormBoundaryAWGt00qual97XRDu");
		Assert.assertTrue(driverReslut.getResult().contains("parent.query_ssh_tunnel_status();"));
	}
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "testid"})
	@Test
	public void uploadPrivateKeyToBroadcasterServer(String userName, String userPass, String login_ip, String uiport, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, testid });
		
		driverReslut = ((FeederPostKeyDriver) testDriver).testIMPL2(userName, userPass, login_ip, uiport);
		Assert.assertTrue(driverReslut.getResult().contains("SSL private key is uploaded successfully"));
	}
}
