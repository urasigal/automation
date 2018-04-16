package com.zixi.load.testing;

import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultipleCreationDriver;
import com.zixi.load.drivers.BroadcasterMultipleUdpDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterMultipleUdpManualTest extends BaseTestZixiMainComponents
{
	//The method will be run before the first test's method invocation in the current class.
		@BeforeClass
		public void testInit() { testDriver = new BroadcasterMultipleUdpDriver(); }
		
		@Parameters({ "userName_bx1", "userName_bx2", "userPass_bx1", "userPass_bx2", "login_ip_bx1", "login_ip_bx2","ts_port","id_bx1","rtp_type", 				
		"multi_src","max_bitrate", "time_shift", "mcast_ip", "mcast_force", "mcast_port", "nic", "type_bx1", "multicast", "enc_key","kompression",			
		"uiport_bx1", "mcast_ttl", "enc_type", "mcast_out", "complete", "max_outputs", "on", "port", "stream", "streamname", "host", "id_bx2", "rtp",
		"fec", "smoothing", "ttl", "remux_bitrate", "df", "local_port", "dec_key", "type_bx2", "rows", "remux_buff", "local_ip", "remux_restampdts", 
		"uiport_bx2", "remux_pcr", "dec_type", "cols", "number_of_streams", "testid"})
		@Test
		public void broadcasterMultipleUdpCreation(String userName_bx1, String userName_bx2, String userPass_bx1, String userPass_bx2, String login_ip_bx1,String login_ip_bx2,
		String ts_port,String id_bx1, String rtp_type, String multi_src, String max_bitrate, String time_shift, String mcast_ip, String mcast_force, String mcast_port, 
		String nic, String type_bx1, String multicast, String enc_key,String kompression, String uiport_bx1, String mcast_ttl, String enc_type, String  mcast_out,
		String complete, String max_outputs, String on, String port,String stream, String streamname, String host, String id_bx2, String rtp,
		String fec, String smoothing, String ttl, String remux_bitrate, String df, String local_port, String dec_key, String type_bx2, String rows, 
		String remux_buff, String local_ip, String remux_restampdts, String uiport_bx2, String remux_pcr, String dec_type, String cols, String number_of_streams,
		String testid) throws Exception {
			
			productAboutDriver.getBroadcasterVersion(login_ip_bx1, uiport_bx1, userName_bx1, userPass_bx1);
			sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "pidof zixi_broadcaster");
			
			// Gather the test parameters in order to pass them to the TestLink
			buildTestParametersString(new String[] { "userName_bx1", "userName_bx2", "userPass_bx1", "userPass_bx2", "login_ip_bx1", "login_ip_bx2","ts_port","id_bx1","rtp_type", 				
			"multi_src","max_bitrate", "time_shift", "mcast_ip", "mcast_force", "mcast_port", "nic", "type_bx1", "multicast", "enc_key","kompression",			
			"uiport_bx1", "mcast_ttl", "enc_type", "mcast_out", "complete", "max_outputs", "on", "port", "stream", "streamname", "host", "id_bx2", "rtp",
			"fec", "smoothing", "ttl", "remux_bitrate", "df", "local_port", "dec_key", "type_bx2", "rows", "remux_buff", "local_ip", "remux_restampdts", 
			"uiport_bx2", "remux_pcr", "dec_type", "cols", "number_of_streams", "testid"}, 
			 new String[] {userName_bx1, userName_bx2, userPass_bx1, userPass_bx2, login_ip_bx1, login_ip_bx2, ts_port, id_bx1, rtp_type, 				
			multi_src,  max_bitrate, time_shift, mcast_ip, mcast_force, mcast_port, nic, type_bx1, multicast, enc_key, kompression,			
			uiport_bx1, mcast_ttl, enc_type, mcast_out, complete, max_outputs, on, port, stream, streamname, host, id_bx2, rtp,
			fec, smoothing, ttl, remux_bitrate, df, local_port, dec_key, type_bx2, rows, remux_buff, local_ip, remux_restampdts, 
			uiport_bx2, remux_pcr, dec_type, cols, number_of_streams, testid });
			
			driverReslut = ((BroadcasterMultipleUdpDriver) testDriver).testIMPLManual
			(userName_bx1, userName_bx2, userPass_bx1, userPass_bx2, login_ip_bx1, login_ip_bx2, ts_port, id_bx1, rtp_type, 				
			multi_src,  max_bitrate, time_shift, mcast_ip, mcast_force, mcast_port, nic, type_bx1, multicast, enc_key, kompression,			
			uiport_bx1, mcast_ttl, enc_type, mcast_out, complete, max_outputs, on, port, stream, streamname, host, id_bx2, rtp,
			fec, smoothing, ttl, remux_bitrate, df, local_port, dec_key, type_bx2, rows, remux_buff, local_ip, remux_restampdts, 
			uiport_bx2, remux_pcr, dec_type, cols, number_of_streams);
			
			// The actual test method.
			Assert.assertEquals(driverReslut.getResult(), "pass");
			
			// Checking if broadcaster has crashes while execution of the test.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "pidof zixi_broadcaster"));
		} 
}
