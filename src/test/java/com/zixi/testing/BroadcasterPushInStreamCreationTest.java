package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushInStreamCreationDriver;

public class BroadcasterPushInStreamCreationTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterPushInStreamCreationDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "latency", "time_shift","force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
	"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on", "password" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String latency, String time_shift, String force_p2p, String mcast_ip,
	String mcast_force, String mcast_port, String type, String uiport, String analyze, String mcast_ttl, String id, String mcast_out,
	String complete, String max_outputs, String on, String password, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");

		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "latency", "time_shift", "force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
		"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete", "max_outputs", "on", "password" ,"testid"}, 
				
		new String[] {userName, userPass, login_ip, latency, time_shift, force_p2p, mcast_ip, mcast_force, mcast_port, type,
		uiport, analyze, mcast_ttl, id, mcast_out, complete, max_outputs, on, password ,testid });
		
		driverReslut = ((BroadcasterPushInStreamCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, latency, time_shift,
		force_p2p, mcast_ip, mcast_force, mcast_port, type, uiport, analyze, mcast_ttl, id, mcast_out, complete, max_outputs, on, password);
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals( driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
