package com.zixi.load.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultipleBondingDriver;
import com.zixi.testing.BaseTest;

public class BroadcasterMultipleBondingTest extends BaseTest{
	
	//The method will be run before the first test's method invocation in the current class.
	@Parameters({"testid"})
	@BeforeClass
	public void testInit(String testid) {
	this.testid = testid;
	testDriver = new BroadcasterMultipleBondingDriver(testFlowDescriptor);
	}
	
	// This is the test parameters that will be provided through suite XML file.
	@Parameters({ "userName_src", "userPass_src", "login_ip_src", "uiport_src", "type_src", "name", "stream", "matrix", 
	"alias", "session", "link_a", "link_b", "bond_links", "latency_src", "fec_force", "fec_overhead",
	"fec_block", "fec_adaptive", "mmt", "fec_aware", "stats_hist", "userName_dst", "userPass_dst", "login_ip_dst", "latency_dst", "time_shift", "force_p2p",
	"mcast_ip", "mcast_force", "mcast_port", "type_dst", "uiport_dst", "analyze", "mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on",
	"password", "dec_type", "dec_key", "number_of_streams", "testid" })
	
	// This test will create a number of bonded output streams from feeder to broadcaster.
	@Test
	public void broadcasterMultiplePushBondedTest(String userName_src, String userPass_src, String login_ip_src, String uiport_src, 
			String type_src, String name, String stream, String matrix, String alias, String session, String link_a, String link_b, String bond_links,
			String latency_src, String fec_force, String fec_overhead,String fec_block, String fec_adaptive, String mmt, String fec_aware, String stats_hist, 
			String userName_dst, String userPass_dst, String login_ip_dst, String latency_dst, String time_shift, String force_p2p, String mcast_ip, String mcast_force,
			String mcast_port, String type_dst, String uiport_dst, String analyze, String mcast_ttl, String id, String mcast_out, String complete,
			String max_outputs, String  on, String password, String dec_type, String dec_key, String number_of_streams, String testid) throws Exception {
		
		testFlowDescriptor.append("\n Start the test BroadcasterMultipleBondingTest.broadcasterMultiplePushBondedTest");
		this.version = productAboutDriver.getBroadcasterVersion(login_ip_src, uiport_src, userName_src, userPass_src);
		
		// Here we take PIDs from a two broadcaster servers because of in this particular test case a two different broadcasters are involved.
		sutProcessId         = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_src,  "22",  "pidof zixi_broadcaster");
		String sutProcessId2 = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_dst,  "22",  "pidof zixi_broadcaster");
		
		// Gather the test parameters in order to pass them to the TestLink
		testParameters = buildTestParametersString(new String[] { "userName_src", "userPass_src", "login_ip_src", "uiport_src", "type_src", "name", "stream", "matrix", 
		"alias", "session", "link_a", "link_b", "bond_links", "latency_src", "fec_force", "fec_overhead",
		"fec_block", "fec_adaptive", "mmt", "fec_aware", "stats_hist", "userName_dst", "userPass_dst", "login_ip_dst", "latency_dst", "time_shift", "force_p2p",
		"mcast_ip", "mcast_force", "mcast_port", "type_dst", "uiport_dst", "analyze", "mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on",
		"password", "dec_type", "dec_key", "number_of_streams", "testid" }, 	
		 new String[] { userName_src, userPass_src, login_ip_src, uiport_src,type_src, name, stream, matrix, alias, session, link_a, link_b, bond_links,
		 latency_src, fec_force, fec_overhead, fec_block, fec_adaptive, mmt, fec_aware, stats_hist, 
		 userName_dst, userPass_dst, login_ip_dst, latency_dst,  time_shift, force_p2p,  mcast_ip, mcast_force,mcast_port, type_dst, uiport_dst, analyze, mcast_ttl, id,
		 mcast_out, complete, max_outputs,  on, password, dec_type, dec_key, number_of_streams, testid });
		
		// Actual test method.
		Assert.assertEquals(((BroadcasterMultipleBondingDriver) testDriver).testIMPL
		( userName_src,  userPass_src,  login_ip_src,  uiport_src, type_src,  name,  stream,  matrix, alias,  session, link_a, link_b, bond_links,
		 latency_src, fec_force, fec_overhead, fec_block, fec_adaptive,  mmt, fec_aware, stats_hist, userName_dst, userPass_dst, login_ip_dst,  
		 latency_dst, time_shift, force_p2p, mcast_ip, mcast_force,mcast_port, type_dst, uiport_dst, analyze, mcast_ttl, id, mcast_out, complete, 
		 max_outputs, on, password, dec_type, dec_key, number_of_streams), "pass");
		
		// Checking if broadcaster has crashes while execution of the test.
		// This is special case because of here a two broadcaster are involved.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid ("root",  "zixiroot1234",  login_ip_src,  "22",  "pidof zixi_broadcaster"));
		Assert.assertEquals(sutProcessId2, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_dst,  "22",  "pidof zixi_broadcaster"));
	} 
}
