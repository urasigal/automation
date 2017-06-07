package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FeederInputStreamDeletionDriver;
import com.zixi.drivers.drivers.FeederInputUdpDriver;

public class FeederInputStreamDeletionTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new FeederInputStreamDeletionDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "mip", "port", "ip", "testid" })
	@Test
	public void feederUdpInputTest(String userName, String userPass, String login_ip, String uiport, String mip, String port, String ip , String testid) 
	throws Exception {	
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "mip", "port", "ip", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, mip, port, ip, testid});
		driverReslut = ((FeederInputStreamDeletionDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, mip, port, ip);
		Assert.assertEquals(driverReslut.getResult(), "Input deleted.");
	}
}
