package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterTrannscodeStreamDriver;

public class BroadcasterIntelTrannscodeStreamTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterTrannscodeStreamDriver();}

	@Parameters({"userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
	"fast_connect", "kompression", "enc_type", "enc_key", "rec_history", "rec_duration", "src", "ap", "ll", "all_pids",
	"bit", "profile_name", "mode", "vp", "smoothing", "max_va_diff_ms", "testid"})
	
	@Test // This test is actually transcodes an input test.
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String login_ip, String uiport, String type,String id,
	String matrix, String max_outputs, String mcast_out, String time_shift, String old,
	String fast_connect, String kompression, String enc_type, String enc_key,
	String rec_history, String rec_duration, String src, String ap, String ll, String all_pids,
	String bit, String profile_name, String mode, String vp, String smoothing, String max_va_diff_ms, String testid)
	throws Exception {

		productAboutDriver.getBroadcasterVersion(login_ip,uiport, userName, userPass);

		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
		"fast_connect", "kompression", "enc_type", "enc_key", "rec_history", "rec_duration", "src", "ap", "ll", "all_pids", "bit", "profile_name", "mode", "vp", "smoothing", "max_va_diff_ms", "testid" },
		new String[] { userName,  userPass,  login_ip,  uiport,  type, id,  matrix,  max_outputs,  mcast_out,  time_shift,  old,
		fast_connect,  kompression,  enc_type,  enc_key, rec_history,  rec_duration,  src,  ap,  ll,  all_pids,  bit,  profile_name, mode, vp, smoothing, max_va_diff_ms, testid });

		driverReslut = ((BroadcasterTrannscodeStreamDriver) testDriver) .testIMPL( userName,  userPass,  login_ip,  uiport,  type, id,
	    matrix,  max_outputs,  mcast_out,  time_shift,  old, fast_connect,  kompression,  enc_type,  enc_key,  rec_history,  rec_duration,  src, ap,  ll,  all_pids,
	    bit,  profile_name, mode, vp, smoothing, max_va_diff_ms);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
	}
}
