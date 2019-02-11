package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FeederReverseTunnelParametersDriver;
import com.zixi.drivers.drivers.FeederSettingsDriver;
import com.zixi.drivers.drivers.ZenFeedersData;

public class FeederSettingsZenTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederSettingsDriver(); }
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "zenHost", "ssh_port", "ssh_user", "zenUser", "zenPass", "zenUiPort", "zenFeederName","testid"})
	@Test
	public void setFeederTunnelParameters(String userName, String userPass, String login_ip, String uiport, String zenHost,
	String ssh_port, String ssh_user, String zenUser, String zenPass, String zenUiPort, String zenFeederName, String testid) throws Exception {
		
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "zenHost", "ssh_port", "ssh_user", "zenUser", "zenPass", "zenUiPort", "zenFeederName","testid"}, 
		new String[] { userName, userPass, login_ip, uiport, zenHost, ssh_port, ssh_user, zenUser, zenPass, zenUiPort, zenFeederName, testid });
		
		// Set ssh connectivity on the feeder side.
		driverReslut = ( (FeederSettingsDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, "zixi.stagingio.devcloud.zixi.com", ssh_port, ssh_user);
		
		// Set reverse tunnel parameters on the feeder's side.
		FeederReverseTunnelParametersDriver feederReverseTunnelParametersDriver = new FeederReverseTunnelParametersDriver();
		feederReverseTunnelParametersDriver.testIMPL(userName, userPass, login_ip, uiport, "1", "4200", "127.0.0.1" ,
		ZenFeedersData.getZenFeederRemoteTunnelPort(zenUser, zenPass, zenHost, zenUiPort, zenFeederName));

		Assert.assertEquals(driverReslut.getResult(), "zixi.stagingio.devcloud.zixi.com");
	}

}
