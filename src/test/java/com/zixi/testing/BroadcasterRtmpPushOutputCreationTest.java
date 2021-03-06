package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterRtmpPushOutputCreationDriver;

public class BroadcasterRtmpPushOutputCreationTest extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterRtmpPushOutputCreationDriver(); }

	// It is a test parameters from suite xml file.
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "type", "name", "stream", "matrix", "url", "url_alt", "rtmp_stream",
	"user", "bandwidth", "latency", "reconnect", "sendfi", "disconnect_low_br", "static_latency", "dec_type", "dec_key", "password", "testid" })
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip, String userName, String userPassword, String uiport, String type, String name, 
	String stream, String matrix, String url, String url_alt, String rtmp_stream, String user, String bandwidth,
	String latency, String reconnect, String sendfi, String disconnect_low_br, String static_latency, String dec_type, String dec_key, String password, String testid) throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		// This is data structure to store a test parameters then provide it to TestLink integration.
	    buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "uiport", "type",
		"name", "stream", "matrix", "url", "url_alt", "rtmp_stream", "user", "bandwidth", "latency", "reconnect", "sendfi",
		"disconnect_low_br", "static_latency", "dec_type", "dec_key", "password", "testid" }, 
		new String[] { login_ip, userName, userPassword, uiport, type, name, stream, matrix, url, url_alt, rtmp_stream, user, bandwidth, latency, reconnect, sendfi,
		disconnect_low_br, static_latency, dec_type, dec_key, password, testid  });
		
		driverReslut = ((BroadcasterRtmpPushOutputCreationDriver) testDriver) .testIMPL(login_ip, userName, userPassword, uiport, type, name, stream, matrix, url, url_alt, 
		rtmp_stream, user, bandwidth, latency, reconnect, sendfi, disconnect_low_br, static_latency, dec_type, dec_key, password);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals( driverReslut.getResult(), "Output " +name+ " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
