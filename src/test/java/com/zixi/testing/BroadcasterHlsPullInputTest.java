package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterHlsPullInputDriver;
import com.zixi.drivers.drivers.BroadcasterPushInStreamCreationDriver;

public class BroadcasterHlsPullInputTest extends BaseTest {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterHlsPullInputDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "enc_type", "enc_key", "old", "fast_connect",
		"kompression", "rec_history", "rec_duration", "rec_path", "rec_template", "hls_url", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String type, String id, String matrix, String max_outputs,
	String mcast_out, String time_shift, String enc_type, String enc_key, String old, String fast_connect,
	String kompression, String rec_history, String rec_duration, String rec_path, String rec_template, String hls_url, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "enc_type", "enc_key", "old", "fast_connect",
				"kompression", "rec_history", "rec_duration", "rec_path", "rec_template", "hls_url", "testid"}, 
				
		new String[] {userName, userPass, login_ip, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, enc_type, enc_key, old, fast_connect,
		kompression, rec_history, rec_duration, rec_path, rec_template, hls_url, testid});
		
		driverReslut = ((BroadcasterHlsPullInputDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, enc_type, enc_key, old, fast_connect,
		kompression, rec_history, rec_duration, rec_path, rec_template, hls_url);
		
		Assert.assertEquals( driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}