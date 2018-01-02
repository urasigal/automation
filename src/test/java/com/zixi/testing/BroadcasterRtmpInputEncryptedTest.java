package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterRtmpInCreationDriver;


// The goal of the test is to verify the broadcaster RTMP Pull input connection.
// The broadcaster RTMP Pull input connection is defined as an ability of a broadcaster server to establish a RTMP connection with 
// an another broadcaster when the RTMP connection initiator will be the receiving broadcaster server.
public class BroadcasterRtmpInputEncryptedTest extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterRtmpInCreationDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "enc_type","enc_key", "disconnect_low_br", "rtmp_nulls", "id","rtmp_url", "rtmp_name", "time_shift", "mcast_ip", "mcast_force",
	"mcast_port", "type", "rtmp_user", "rtmp_bitrate", "rtmp_passwd", "uiport", "mcast_ttl", "rtmp_latency", "mcast_out", "complete", "max_outputs", "on" ,"testid"})
	@Test
	public void broadcasterRtmpPullTest(String userName, String userPass,
	String login_ip, String enc_type, String enc_key, String disconnect_low_br, String rtmp_nulls, String id, String rtmp_url,
	String rtmp_name, String time_shift, String mcast_ip,
	String mcast_force, String mcast_port, String type,
	String rtmp_user, String rtmp_bitrate, String rtmp_passwd,
	String uiport, String mcast_ttl, String rtmp_latency,
	String mcast_out, String complete, String max_outputs, String on,
	String testid) throws Exception {
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "enc-type","enc-key", "disconnect_low_br","rtmp_nulls", "id",
		"rtmp_url", "rtmp_name", "time_shift", "mcast_ip", "mcast_force", "mcast_port", "type", "rtmp_user", "rtmp_bitrate", "rtmp_passwd",
		"uiport", "mcast_ttl", "rtmp_latency", "mcast_out", "complete","max_outputs", "on" ,"testid"}, 
		new String[] {userName, userPass, login_ip, enc_type, enc_key, disconnect_low_br ,rtmp_nulls, id, rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force,
		mcast_port, type, rtmp_user, rtmp_bitrate, rtmp_passwd, uiport, mcast_ttl, rtmp_latency, mcast_out, complete, max_outputs, on ,testid});
		
		driverReslut = ((BroadcasterRtmpInCreationDriver) testDriver) .testIMPL(userName, userPass, login_ip, enc_type, enc_key, disconnect_low_br, 
		rtmp_nulls, id, rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force, mcast_port, type, rtmp_user, rtmp_bitrate, rtmp_passwd,
		uiport, mcast_ttl, rtmp_latency, mcast_out, complete, max_outputs, on);
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
