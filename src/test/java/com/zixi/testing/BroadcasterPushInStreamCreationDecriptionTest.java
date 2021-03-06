package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushInStreamCreationDriver;
import com.zixi.drivers.drivers.TestDriver;


public class BroadcasterPushInStreamCreationDecriptionTest extends BaseTestZixiMainComponents {
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPushInStreamCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "latency", "time_shift",
	"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type","uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
	"max_outputs", "on", "password", "dec_type", "dec_key", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String latency, String time_shift, String force_p2p, String mcast_ip,
	String mcast_force, String mcast_port, String type, String uiport, String analyze, String mcast_ttl, String id, String mcast_out,
	String complete, String max_outputs, String on, String password, String dec_type, String dec_key, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "latency", "time_shift",
		"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type", "uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
		"max_outputs", "on", "password", "dec_type", "dec_key", "testid"}, 
		new String[] {userName, userPass, login_ip, latency, time_shift, force_p2p, mcast_ip, mcast_force, mcast_port, type,
		uiport, analyze, mcast_ttl, id, mcast_out, complete, max_outputs, on, password, dec_type, dec_key, testid });
		
		driverReslut = ((BroadcasterPushInStreamCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, latency, time_shift,
		force_p2p, mcast_ip, mcast_force, mcast_port, type,uiport, analyze, mcast_ttl, id, mcast_out, complete,
		max_outputs, on, password, dec_type, dec_key);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'"+ " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
