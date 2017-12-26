package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.ReceiverInputStatisticDriver;

public class ReceiverInputStatisticTest extends BaseTestZixiMainComponents {
	
	@BeforeClass
	public void testInit() { testDriver = new ReceiverInputStatisticDriver(); }

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id", "testduration" ,"testid"})
	@Test
	public void receiverInputStatistic(String userName, String userPassword, String login_ip, String uiport, String id, String testduration, String testid) throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver");
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		buildTestParametersString(new String[] { "userName", "userPassword", "login_ip", "uiport", "id", "testduration" ,"testid" }, 
		new String[] {userName, userPassword, login_ip, uiport, id, testduration ,testid });
		
		driverReslut = ((ReceiverInputStatisticDriver) testDriver) .testIMPL(userName, userPassword, login_ip, uiport, id, testduration);
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "tested");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
}
