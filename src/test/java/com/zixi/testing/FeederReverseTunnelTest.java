package com.zixi.testing;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;
import com.zixi.drivers.drivers.FeederReverseTunnelDriver;

public class FeederReverseTunnelTest extends BaseTest{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederReverseTunnelDriver(); }
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "userName", "sshPass", "login_ip", "ssh_port", "ssh_user", "reverse_port", "testid"})
	@Test
	public void uploadPrivateKeyToFeeder(String userName, String sshPass, String login_ip, String ssh_port, String ssh_user, String reverse_port, String testid) 
	throws InterruptedException, IOException, JSchException {
		
		// Provide parameters to a TestLink.
		testLinktestParameters = buildTestParametersString(new String[] { "userName", "sshPass", "login_ip", "ssh_port", "ssh_user", "reverse_port", "testid"}, 
		new String[] { userName, sshPass, login_ip, ssh_port, ssh_user, reverse_port, testid });
		
		driverReslut = ((FeederReverseTunnelDriver) testDriver).testIMPL(userName, sshPass, login_ip, ssh_port, ssh_user, reverse_port);
		
		Assert.assertEquals(driverReslut.getResult(), "sshd");
	}

}
