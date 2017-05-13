package com.zixi.load.testing;

import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserAllInputStreamDeletorDriver;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterRundomInputStreamDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterRundomInputStreamTest extends BaseTestZixiMainComponents
{
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterRundomInputStreamDriver(testFlowDescriptor); }
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "name", "testid" })
	@Test
	public void broadcasterSwitchUdpOut(String login_ip, String userName, String userPassword, String uiport, String name, String testid) throws Exception {
		
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
				
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "name", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, name, testid });
		
		testFlowDescriptor.append("Begin the test (BroadcasterRundomInputStreamDriver) ");
		
		driverReslut = ((BroadcasterRundomInputStreamDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport, name);
		
		Assert.assertEquals(driverReslut.getResult(), "More than + 80%");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
