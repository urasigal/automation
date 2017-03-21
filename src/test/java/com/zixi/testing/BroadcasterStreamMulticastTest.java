package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSingleUdpInCreationDriver;
import com.zixi.drivers.drivers.BroadcasterStreamMulticastDriver;

public class BroadcasterStreamMulticastTest  extends BaseTest{
	
	@BeforeClass
	public void testInit() {  testDriver = new BroadcasterStreamMulticastDriver(); }

	// Test parameters.
	@Parameters({"bx1_login_ip", "bx1_userName", "bx1_bxuserPassword", "bx1_uiport", "bx2_login_ip", "bx2_userName", "bx2_bxuserPassword",
	"bx2_uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression",
	"enc_type", "rec_history", "rec_duration", "ts_port", "max_bitrate", "multicast", "multi_src", "nic", "device",
	"rtp_type", "enc_key", "testid"})
	@Test
	public void defineMulticastInputStream(String bx1_login_ip, String bx1_userName, String bx1_bxuserPassword, String bx1_uiport, String bx2_login_ip, String bx2_userName, 
	String bx2_bxuserPassword, String bx2_uiport, String type, String id, String matrix, String max_outputs, 
	String mcast_out, String time_shift, String old, String fast_connect, String kompression,
	String enc_type, String rec_history, String rec_duration, String ts_port, String max_bitrate, 
	String multicast, String multi_src, String nic, String device, String rtp_type, String enc_key, String testid) throws Exception {
		
		//Print this class name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(bx1_login_ip, bx1_uiport, bx1_userName, bx1_bxuserPassword);
		
		buildTestParametersString(new String[] {"bx1_login_ip", "bx1_userName", "bx1_bxuserPassword", "bx1_uiport", "bx2_login_ip", "bx2_userName", 
		"bx2_bxuserPassword", "bx2_uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression",
		"enc_type", "rec_history", "rec_duration", "ts_port", "max_bitrate", "multicast", "multi_src", "nic", "device",
		"rtp_type", "enc_key", "testid"}, 
		
		new String[] {bx1_login_ip, bx1_userName, bx1_bxuserPassword, bx1_uiport, bx2_login_ip, bx2_userName, bx2_bxuserPassword,
		bx2_uiport, type, id, matrix, max_outputs, mcast_out, time_shift, old, fast_connect, kompression,
		enc_type, rec_history, rec_duration, ts_port, max_bitrate, multicast, multi_src, nic, device, rtp_type, enc_key, testid});
		
		String multiastIp = ((BroadcasterStreamMulticastDriver) testDriver).testIMPL(bx1_login_ip, bx1_userName, bx1_bxuserPassword, bx1_uiport, id);
		
		driverReslut = new BroadcasterSingleUdpInCreationDriver().testIMPL(bx2_userName, bx2_bxuserPassword, bx2_login_ip, ts_port, id, rtp_type,
		multi_src, max_bitrate, time_shift, "", "", ts_port, nic, type, multiastIp, enc_key,
		kompression, bx2_uiport, "", enc_type, mcast_out, "", max_outputs, "1");
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");	
	}
}