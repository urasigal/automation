package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederOutputDeletionDriver;

public class FeederOutputDeletioinTest extends BaseTest{

	@BeforeClass
	public void testInit() {testDriver = new FeederOutputDeletionDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type","host" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id,
	String mip, String port, String ip, String prog, String chan, String type, String host,String testid) throws Exception {
		
		// Writes test results to the TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip","port", "ip", "prog", "chan", "type","host" ,"testid" }, 
		new String[] { userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type,host ,testid });
		
		driverReslut = ((FeederOutputDeletionDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type, host);
		
		Assert.assertEquals(driverReslut.getResult(), "Output deleted.");
	}
}
