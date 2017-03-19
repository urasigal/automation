package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterAdaptiveGroupDeletionDriver;

public class BroadcasterAdaptiveGroupDeletionTest extends BaseTest{
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterAdaptiveGroupDeletionDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass, String login_ip, String uiport, String name, String testid) throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name", "testid" }, 
						
		new String[] { userName, userPass, login_ip, uiport, name, testid });
		driverReslut = ((BroadcasterAdaptiveGroupDeletionDriver) testDriver).testIMPL( userName, userPass, login_ip, uiport, name);
		
		Assert.assertEquals( driverReslut.getResult(), "{\"success\":1}");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}

}
