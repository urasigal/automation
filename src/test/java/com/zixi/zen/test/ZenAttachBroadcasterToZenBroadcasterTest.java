package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterSetSshParametersDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenAttachBroadcasterToZenBroadcasterTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSetSshParametersDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"bxUserName", "bxUserPass", "bxLogin_ip", "bxUiport", "sshUserName", "sshHost", "ssh_port", "clusterName", "testid"})
	@Test
	public void attachBroadcasterToZenBroadcaster(String bxUserName, String bxUserPass, String bxLogin_ip, String bxUiport, String sshUserName, 
	String sshHost, String ssh_port, String clusterName, String testid)
	throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "bxUserName", "bxUserPass", "bxLogin_ip", "bxUiport", "sshUserName", "sshHost", "ssh_port", "clusterName", "testid"}, 
		new String[] {bxUserName, bxUserPass, bxLogin_ip, bxUiport, sshUserName, sshHost, ssh_port, clusterName, testid});
		
		driverReslut = ((BroadcasterSetSshParametersDriver) testDriver).setHostandSshPortBroadcaster
																		(bxUserName, bxUserPass, bxLogin_ip, bxUiport, sshHost, ssh_port);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}
