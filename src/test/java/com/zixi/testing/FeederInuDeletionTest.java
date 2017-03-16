package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederInputUdpDriver;

public class FeederInuDeletionTest extends BaseTest{

	@BeforeClass
	public void testInit() { testDriver = new FeederInputUdpDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "mip", "port", "ip" , "testid"})
	@Test
	public void feederUdpInputTest(String userName, String userPass, String login_ip, String uiport , String mip, String port , String ip, String name, String ssm ,
	String  rtp_type , String  testid) throws Exception {
		
		// Writes test results to the TestLink.
		testLinktestParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "mip","port", "ip","name", "ssm", "rtp_type", "testid" }, 
		new String[] { userName, userPass, login_ip, uiport, mip,port, ip, name, ssm, rtp_type, testid});
		
		driverReslut = ((FeederInputUdpDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, mip,port, ip, name, ssm, rtp_type);
		
		Assert.assertEquals(driverReslut.getResult(), "Input added");
	}
}
