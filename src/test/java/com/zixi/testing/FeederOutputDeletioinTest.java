package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FeederOutputDeletionDriver;

public class FeederOutputDeletioinTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() {testDriver = new FeederOutputDeletionDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type","host", "push_port" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id,
	String mip, String port, String ip, String prog, String chan, String type, String host, @Optional("2088") String push_port, String testid) throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		// Writes test results to the TestLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip","port", "ip", "prog", "chan", "type","host", "push_port" ,"testid" }, 
		new String[] { userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type,host, push_port, testid });
		
		driverReslut = ((FeederOutputDeletionDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, id, mip, port, 
		ip, prog, chan, type, host, push_port);
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		Assert.assertEquals(driverReslut.getResult(), "Output deleted.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	}
}
