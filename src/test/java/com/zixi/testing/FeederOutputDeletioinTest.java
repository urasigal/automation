package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederOutputDeletionDriver;

public class FeederOutputDeletioinTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() {testDriver = new FeederOutputDeletionDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type","host", "push_port" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id,
	String mip, String port, String ip, String prog, String chan, String type, String host, @Optional("2088") String push_port, String testid) throws Exception {
		
		// Writes test results to the TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip","port", "ip", "prog", "chan", "type","host", "push_port" ,"testid" }, 
		new String[] { userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type,host, push_port, testid });
		
		driverReslut = ((FeederOutputDeletionDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, id, mip, port, 
		ip, prog, chan, type, host, push_port);
		
		Assert.assertEquals(driverReslut.getResult(), "Output deleted.");
	}
}
