package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterStreamMulticastDriver;

public class BroadcasterStreamMulticastTest  extends BaseTest{
	
	@BeforeClass
	public void testInit() { 
		// Super class element
		testDriver = new BroadcasterStreamMulticastDriver();
	}

	// Test parameters.
	@Parameters({"bx1_login_ip", "bx1_userName", "bx1_bxuserPassword", "bx1_uiport", "bx2_login_ip", "bx2_userName", "bx2_bxuserPassword", "bx2_uiport", "prev_id",
	"type", "id", "matrix", "max_outputs", "mcast_out", "mcast_pool", "mcast_force", "time_shift", "old", "fast_connect", "kompression",
	"enc_type", "enc_key", "path", "ts_port", "id_dst", "rtp_type", "multi_src", "max_bitrate", "time_shift_srs", "mcast_ip", "mcast_force_src", "mcast_port",
	"nic", "type_src", "multicast", "enc_key_src", "kompression_src", "uiport_src", "mcast_ttl", "enc_type_src", "mcast_out_src", "complete", "max_outputs_src", "on","testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String bx1_login_ip, String  bx1_userName, String  bx1_bxuserPassword, String bx1_uiport, String bx2_login_ip,
	String bx2_userName, String bx2_bxuserPassword, String bx2_uiport, String prev_id, String type, String id, String matrix, String max_outputs, 
	String mcast_out, String mcast_pool, String mcast_force, String time_shift, String old, String fast_connect, String kompression,
	String enc_type, String enc_key, String path, String ts_port, String id_dst, String rtp_type, String multi_src, String max_bitrate, String time_shift_srs,
	String mcast_ip, String mcast_force_src, String mcast_port, String nic, String type_src, String multicast, String enc_key_src, String kompression_src,
	String uiport_src, String mcast_ttl, String enc_type_src, String mcast_out_src, String complete, String max_outputs_src, String on, String testid) throws Exception {
		
		//Print this class name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", bx1_login_ip, "22", "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(bx1_login_ip, bx1_uiport, bx1_userName, bx1_bxuserPassword);
		
		testParameters = buildTestParametersString(new String[] { "bx1_login_ip", "bx1_userName", "bx1_bxuserPassword", "bx1_uiport", "bx2_login_ip", "bx2_userName", "bx2_bxuserPassword", "bx2_uiport", "prev_id",
		"type", "id", "matrix", "max_outputs", "mcast_out", "mcast_pool", "mcast_force", "time_shift", "old", "fast_connect", "kompression",
		"enc_type", "enc_key", "path", "ts_port", "id_dst", "rtp_type", "multi_src", "max_bitrate", "time_shift_srs", "mcast_ip", "mcast_force_src", "mcast_port",
		"nic", "type_src", "multicast", "enc_key_src", "kompression_src", "uiport_src", "mcast_ttl", "enc_type_src", "mcast_out_src", "complete", "max_outputs_src", "on","testid" }, 
		
		new String[] {bx1_login_ip, bx1_userName, bx1_bxuserPassword, bx1_uiport, bx2_login_ip, bx2_userName, bx2_bxuserPassword, bx2_uiport, prev_id,
		type, id, matrix, max_outputs, mcast_out, mcast_pool, mcast_force, time_shift, old, fast_connect, kompression,
		enc_type, enc_key, path, ts_port, id_dst, rtp_type, multi_src, max_bitrate, time_shift_srs, mcast_ip, mcast_force_src, mcast_port,
		nic, type_src, multicast, enc_key_src, kompression_src, uiport_src, mcast_ttl, enc_type_src, mcast_out_src, complete, max_outputs_src, on, testid});
		
		Assert.assertEquals(((BroadcasterStreamMulticastDriver) testDriver).testIMPL(bx1_login_ip, bx1_userName, bx1_bxuserPassword, bx1_uiport, bx2_login_ip, bx2_userName, bx2_bxuserPassword, bx2_uiport, prev_id,
		type, id, matrix, max_outputs, mcast_out, mcast_pool, mcast_force, time_shift, old, fast_connect, kompression,
		enc_type, enc_key, path, ts_port, id_dst, rtp_type, multi_src, max_bitrate, time_shift_srs, mcast_ip, mcast_force_src, mcast_port,
		nic, type_src, multicast, enc_key_src, kompression_src, uiport_src, mcast_ttl, enc_type_src, mcast_out_src, complete, max_outputs_src, on), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertNotEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234",  bx1_login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}