package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterAddStreamDriver;

public class BroadcasterAddHttpPushInputStreamTest   extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterAddStreamDriver(); }

	@Parameters({ "login_ip", "userName", "userPass", "uiport", "type", "name", "matrix", "stream", "dec_type", "dec_key", "host", "port",  "http", "buffer_size", "testid" })
	@Test
	public void broadcasterAddHttpPushInputStream(String login_ip, String userName, String userPass, String uiport, String type, String name, 
	String matrix, String stream, String dec_type, String dec_key, String host, String port, String http, String buffer_size, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		
		buildTestParametersString(new String[] {"login_ip", "userName", "userPass", "uiport", "type", "name", "matrix", "stream", 
		"dec_type", "dec_key", "host", "port",  "http", "buffer_size", "testid"}, 	
		new String[] { login_ip, userName, userPass, uiport, type, name, matrix, stream, dec_type, dec_key, host, port, http, buffer_size, testid });
		
		driverReslut = ((BroadcasterAddStreamDriver) testDriver).broadcasterAddHttpPushInputStream(login_ip, userName, userPass, uiport, type, name, matrix, stream, dec_type, dec_key, host, port, http, buffer_size);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals( driverReslut.getResult(), "Output " + "'" + name + "'" + " added."); 
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}