package com.zixi.load.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultiplePushDriver;
import com.zixi.load.drivers.FeederBroadcasterBondingMultipleThreeLinkDriver;
import com.zixi.testing.BaseTest;

public class FeederBroadcasterBondingMultipleThreeLinkTest extends BaseTest{
	
		//The method will be run before the first test's method invocation in the current class.
		@Parameters({"testid"})
		@BeforeClass
		public void testInit(String testid) {
		this.testid = testid;
		testDriver = new FeederBroadcasterBondingMultipleThreeLinkDriver();
		}
		
		@Parameters({ 
		"userName_feeder", "userPass_feeder", "login_ip_feeder", "name", "mip", "port", "ip", "prog", "chan", "type_feeder", 
		"ostr", "oses", "oetp", "oeky", "obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force", "fec_adaptive", "ofec",
		"ofecl", "stop_on_drop", "mmt", "smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr", "rtmp_feedback", "group", 
		"bonded", "bond_host1", "bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2",
		"bond_limit2", "bond_backup2", "bond_host3",  "bond_port3", "bond_nic3", "bond_limit3", "bond_backup3", "uiport_feeder",
		"userName_broadcaster", "userPass_broadcaster", "login_ip_broadcaster", "latency",
		"time_shift", "force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type_broadcaster", "uiport_broadcaster", "analyze", 
		"mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on", "password", "dec_type", "dec_key", "number_of_streams", "testid"})
		
		
		// This test will create a number of bonded output streams from feeder to broadcaster.
		@Test
		public void broadcasterMultiplePullInCreation(String userName_feeder, String userPass_feeder, String login_ip_feeder, String name, String mip, String port, 
			String ip, String prog, String chan, String type_feeder, String ostr, String oses, String oetp, String oeky, String obit, 
			String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive, String ofec, String ofecl, 
			String stop_on_drop, String mmt, String smoothing, String limited, String minbps, String lim_enc_addr, 
			String pad_to_cbr, String rtmp_feedback, String group, String bonded, String bond_host1, String bond_port1, String bond_nic1, 
			String bond_limit1, String bond_backup1, String bond_host2, String bond_port2, String bond_nic2, String bond_limit2, String bond_backup2, 
			String bond_host3, String bond_port3, String bond_nic3, String bond_limit3, String bond_backup3, String uiport_feeder, String userName_broadcaster, 
			String userPass_broadcaster, String login_ip_broadcaster, String latency, String time_shift, String force_p2p, String mcast_ip, String mcast_force,
			String mcast_port, String type_broadcaster, String uiport_broadcaster, String analyze, String mcast_ttl, String id, String mcast_out, String complete, 
			String max_outputs, String on, String password, String dec_type, String dec_key, String number_of_streams, String testid) throws Exception {
			
			
			this.version = productAboutDriver.getBroadcasterVersion(login_ip_feeder, uiport_feeder, userName_feeder, userPass_feeder);
			
			// Here we take PIDs from a two broadcaster servers because of in this particular test case a two different broadcasters are involved.
			sutProcessId         = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_feeder,  "22",  "pidof zixi_feeder");
			String sutProcessId2 = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_broadcaster,  "22",  "pidof zixi_broadcaster");
			
			// Gather the test parameters in order to pass them to the TestLink
			testParameters = buildTestParametersString(new String[] { "userName_feeder", "userPass_feeder", "login_ip_feeder", "name", "mip", "port", "ip", "prog", "chan", "type_feeder", 
			"ostr", "oses", "oetp", "oeky", "obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force", "fec_adaptive", "ofec",
			"ofecl", "stop_on_drop", "mmt", "smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr", "rtmp_feedback", "group", 
			"bonded", "bond_host1", "bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2",
			"bond_limit2", "bond_backup2", "bond_host3",  "bond_port3", "bond_nic3", "bond_limit3", "bond_backup3", "uiport_feeder",
			"userName_broadcaster", "userPass_broadcaster", "login_ip_broadcaster", "latency",
			"time_shift", "force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type_broadcaster", "uiport_broadcaster", "analyze", 
			"mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on", "password", "dec_type", "dec_key", "number_of_streams", "testid" }, 
					
			 new String[] { 
			 userName_feeder, userPass_feeder, login_ip_feeder, name, mip, port,  ip,  prog, chan, type_feeder, ostr, oses, oetp, oeky, obit, 
			 olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt, smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,
			 rtmp_feedback, group, bonded, bond_host1, bond_port1, bond_nic1, bond_limit1, bond_backup1, bond_host2, bond_port2, bond_nic2, bond_limit2, 
			 bond_backup2, bond_host3, bond_port3, bond_nic3, bond_limit3, bond_backup3, uiport_feeder, userName_broadcaster, userPass_broadcaster, login_ip_broadcaster, latency, time_shift, force_p2p, mcast_ip, 
			 mcast_force, mcast_port, type_broadcaster, uiport_broadcaster, analyze, mcast_ttl, id, mcast_out, complete, max_outputs, on, password, dec_type,
			 dec_key, number_of_streams, testid });
			
			// Actual test method.
			Assert.assertEquals(((FeederBroadcasterBondingMultipleThreeLinkDriver) testDriver).testIMPL
			(userName_feeder, userPass_feeder, login_ip_feeder, name, mip, port,  ip,  prog, chan, type_feeder, ostr, oses, oetp, oeky, obit, 
			 olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt, smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,
			 rtmp_feedback, group, bonded, bond_host1, bond_port1, bond_nic1, bond_limit1, bond_backup1, bond_host2, bond_port2, bond_nic2, bond_limit2, 
			 bond_backup2, bond_host3, bond_port3, bond_nic3, bond_limit3, bond_backup3, uiport_feeder, userName_broadcaster, userPass_broadcaster, login_ip_broadcaster, 
			 latency, time_shift, force_p2p, mcast_ip, mcast_force, mcast_port, type_broadcaster, uiport_broadcaster, analyze, mcast_ttl, id, mcast_out, complete, 
			 max_outputs, on, password, dec_type, dec_key, number_of_streams), "pass");
			
			// Checking if broadcaster has crashes while execution of the test.
			// This is special case because of here a two broadcaster are involved.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid ("root",  "zixiroot1234",  login_ip_feeder,  "22",  "pidof zixi_feeder"));
			Assert.assertEquals(sutProcessId2, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", login_ip_broadcaster,  "22",  "pidof zixi_broadcaster"));
		} 
}
