package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterTrannscodeStreamDriver;

public class BroadcasterTrannscodeStreamTest extends BaseTestZixiMainComponents {
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterTrannscodeStreamDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
	"fast_connect", "kompression", "enc_type", "enc_key", "rec_history", "rec_duration", "src", "ap", "bit", "profile_name", "testid" })
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String login_ip, String uiport, String type,
	String id, String matrix, String max_outputs, String mcast_out, String time_shift, String old, String fast_connect,
	String kompression, String enc_type, String enc_key, String rec_history, String rec_duration, String src, String ap,
	String bit, String profile_name, String testid) throws Exception {

		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id",
		"matrix", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression", "enc_type", "enc_key",
		"rec_history", "rec_duration", "src", "ap", "bit", "profile_name","testid"}, 
				
		new String[] {userName, userPass, login_ip, uiport, type, id,  matrix, max_outputs, mcast_out, time_shift, old,
		fast_connect, kompression, enc_type, enc_key, rec_history, rec_duration, src, ap, bit, profile_name, testid });
		
		
		Assert.assertEquals(((BroadcasterTrannscodeStreamDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, type, id,
		matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key,
	     rec_history, rec_duration, src, ap, bit, profile_name), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
				
	}
}
