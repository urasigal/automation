package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterCreateAddaptiveGroupDriver;

public class BroadcasterRecordAdaptiveGroupTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterCreateAddaptiveGroupDriver(); }
	
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "group_name", "record", "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass, String login_ip, String uiport, String group_name, String record,  String testid)throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
	
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "group_name", "record", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, group_name, record, testid });

		driverReslut = ((BroadcasterCreateAddaptiveGroupDriver) testDriver).broadcasterAdaptiveGroupRecordingOn(userName, userPass, login_ip, uiport, group_name, record, testid );
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Recording has been started");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
