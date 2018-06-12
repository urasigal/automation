package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ReceiverSshConnectionDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenAttachReceiverToZenREceiverTest  extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new ReceiverSshConnectionDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"receiverUserName", "receiverUserPass", "receiverLogin_ip", "receiverUiport", "sshHost", "ssh_port", "ssh_user", "testid"})
	@Test
	public void attachReceiverToREceiverZen(String receiverUserName,  String receiverUserPass, String receiverLogin_ip, 
	String receiverUiport, String sshHost, String ssh_port, String ssh_user, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"receiverUserName", "receiverUserPass", "receiverLogin_ip", "receiverUiport", "sshHost", "ssh_port", "ssh_user", "testid"}, 
		new String[] {receiverUserName, receiverUserPass, receiverLogin_ip, receiverUiport, sshHost, ssh_port, ssh_user, testid});
		driverReslut = ((ReceiverSshConnectionDriver) testDriver).setSshUserAndSshHostReceiver(
						 receiverUserName,  receiverUserPass, receiverLogin_ip, receiverUiport, sshHost, ssh_port, ssh_user + "-receiver");
		driverReslut = ((ReceiverSshConnectionDriver) testDriver).uploadSshKeyToReceiverZen( receiverUserName, receiverUserPass, receiverLogin_ip, receiverUiport);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}