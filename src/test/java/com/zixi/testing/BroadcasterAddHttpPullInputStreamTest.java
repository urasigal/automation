package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterAddStreamDriver;
import com.zixi.drivers.drivers.BroadcasterPushInStreamCreationDriver;

public class BroadcasterAddHttpPullInputStreamTest  extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterAddStreamDriver(); }

	@Parameters({ "login_ip", "userName", "userPass", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "enc_type", "enc_key", "old", "fast_connect", 
	"kompression", "rec_history", "rec_duration", "rec_path", "rec_template", "ts_http_url", "smoothing_latency", "testid" })
	@Test
	public void broadcasterAddHttpPullInputStream(String login_ip, String userName, String userPass, String uiport, String type, String id, String matrix, String max_outputs, 
	String mcast_out, String time_shift, String enc_type, String enc_key, String old, String fast_connect,
	String kompression, String rec_history, String rec_duration,String rec_path, String rec_template, String ts_http_url,
	String smoothing_latency, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		
		buildTestParametersString(new String[] {"login_ip", "userName", "userPass", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "enc_type", "enc_key", "old", "fast_connect", 
		"kompression", "rec_history", "rec_duration", "rec_path", "rec_template", "ts_http_url", "smoothing_latency", "testid"}, 	
		new String[] {login_ip, userName, userPass, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, enc_type, enc_key, old, fast_connect, kompression, rec_history, rec_duration,
		rec_path, rec_template, ts_http_url, smoothing_latency, testid });
		
		driverReslut = ((BroadcasterAddStreamDriver) testDriver).broadcasterAddHttpPullInputStream(login_ip, userName, userPass, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, 
		enc_type, enc_key, old, fast_connect, kompression, rec_history, rec_duration, rec_path, rec_template, ts_http_url, smoothing_latency);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals( driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}