package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.ReceiverAddFaioverGroupDriver;
import com.zixi.drivers.drivers.ReceiverOutputStreamDeletionDriver;

public class ReceiverAddFaioverGroupTest  extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {testDriver = new ReceiverAddFaioverGroupDriver();}

	@Parameters({ "login_ip", "userName", "userPass", "uiport", "group_name", "group_streams", "latency",
	"max_bitrate", "testid"})
	@Test		
	public void receiverAddFailoverGroupTest(String login_ip, String userName, String userPass, String uiport, 
	String group_name, String group_streams, String latency, String max_bitrate, String testid) 
	throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		buildTestParametersString(new String[] { "login_ip", "userName", "userPass", "uiport", "group_name", "group_streams", "latency",
		"max_bitrate", "testid" }, 	
		new String[] { login_ip, userName, userPass, uiport, group_name, group_streams, latency, max_bitrate, testid});
		
		driverReslut = ((ReceiverAddFaioverGroupDriver) testDriver).addFailoverGroup( login_ip, userName, userPass, uiport, group_name, group_streams, latency, max_bitrate);		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertTrue(driverReslut.getResult().contains("added"));
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
}
