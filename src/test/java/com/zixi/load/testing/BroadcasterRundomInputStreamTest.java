package com.zixi.load.testing;

import java.sql.Timestamp;
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
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "name", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, name, testid });	
		testFlowDescriptor.append("Begin the test (BroadcasterRundomInputStreamDriver) ");
		driverReslut = ((BroadcasterRundomInputStreamDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport, name);
	
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "More than + 80%");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
