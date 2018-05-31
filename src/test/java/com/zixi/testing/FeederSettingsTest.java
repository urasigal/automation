package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FeederReverseTunnelParametersDriver;
import com.zixi.drivers.drivers.FeederSettingsDriver;
import com.zixi.drivers.drivers.ZenFeedersData;

public class FeederSettingsTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederSettingsDriver(); }
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "host", "ssh_port", "ssh_user", "zenFeederName","testid"})
	@Test
	public void setFeederTunnelParameters(String userName, String userPass, String login_ip, String uiport, String host,
	String ssh_port, String ssh_user, String zenFeederName, String testid) throws Exception {
		
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "host", "ssh_port", "ssh_user", "zenFeederName","testid"}, 
		new String[] { userName, userPass, login_ip, uiport, host, ssh_port, ssh_user, zenFeederName, testid });
		
		driverReslut = ( (FeederSettingsDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, host, ssh_port, ssh_user);
		FeederReverseTunnelParametersDriver feederReverseTunnelParametersDriver = new FeederReverseTunnelParametersDriver();
		feederReverseTunnelParametersDriver.testIMPL(userName, userPass, login_ip, uiport, "1", "4200", "127.0.0.1" ,
		ZenFeedersData.getZenFeederRemoteTunnelPort(userName, userPass, host, uiport, zenFeederName));

		Assert.assertEquals(driverReslut.getResult(), host);
	}

}
