package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcasterFailOverInputCreationDriver;
import com.zixi.drivers.drivers.BroadcasterFileInputDriver;

public class BroadcasterFailOverInputCreationTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterFailOverInputCreationDriver();
	}
	
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
	"fast_connect", "kompression", "enc_type", "enc_key", "latency", "max_bitrate", "keep_rtp", "group_components", "testid" })
	@Test
	public void broadcasterCreateFileInout(String userName, String userPass, String login_ip, String uiport, String type, String id, String matrix, String max_outputs,
	String mcast_out, String time_shift, String old, String fast_connect, String kompression, String enc_type, String enc_key, String latency, String max_bitrate, String keep_rtp,
	String group_components, String testid) throws Exception {

		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
		"fast_connect", "kompression", "enc_type", "enc_key", "latency", "max_bitrate", "keep_rtp", "group_components", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key,latency, max_bitrate,
		keep_rtp, group_components, testid });
		
		driverReslut = ((BroadcasterFailOverInputCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, type, id, matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, enc_key,latency, max_bitrate,
	    keep_rtp, group_components);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
	}
}
