package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterRtmpPushInputStreamDriver;

public class BroadcasterRtmpPushInputCreationStreamTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterRtmpPushInputStreamDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix",
	"max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression", "enc_type", "enc_key", "rec_history",
	"rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "testid" })
	// The goal of the test is to verify adding of a RTMP push input stream.
	@Test
	public void broadcasterRtmpPushTest(String userName, String userPass,
	String login_ip, String uiport,String type, String id, String matrix,
	String max_outputs, String mcast_out, String time_shift, String old, String fast_connect, String kompression,
	String enc_type, String enc_key, String rec_history,String rec_duration, String rtmp_url, String rtmp_name,
	String rtmp_user, String testid) throws Exception 
	{		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");	
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix",
		"max_outputs", "mcast_out", "time_shift", "old", "fast_connect",
		"kompression", "enc_type", "enc_key", "rec_history",
		"rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "testid" }, 
	    new String[] { userName, userPass, login_ip, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, old, fast_connect,
		kompression, enc_type, enc_key, rec_history, rec_duration, rtmp_url, rtmp_name, rtmp_user, testid });
		
		driverReslut = ((BroadcasterRtmpPushInputStreamDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, type, id, matrix,
		max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key, rec_history, rec_duration, rtmp_url, rtmp_name, rtmp_user);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
		//LOGGER.exiting(getClass().getName(), "broadcasterRtmpPushTest");
	}
}
