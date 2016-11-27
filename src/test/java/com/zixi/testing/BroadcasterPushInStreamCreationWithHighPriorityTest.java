package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterPushInStreamCreationDriver;
import com.zixi.drivers.drivers.TestDriver;

public class BroadcasterPushInStreamCreationWithHighPriorityTest extends BaseTest {
	
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPushInStreamCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "latency", "time_shift",
			"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
			"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
			"max_outputs", "on", "password", "priority_ids" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String login_ip, String latency,
			String time_shift, String force_p2p, String mcast_ip,
			String mcast_force, String mcast_port, String type, String uiport,
			String analyze, String mcast_ttl, String id, String mcast_out,
			String complete, String max_outputs, String on, String password, String priority_ids,
			String testid) throws Exception {
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "latency", "time_shift",
				"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
				"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
				"max_outputs", "on", "password", "priority_ids" ,"testid"}, 
				
				new String[] {userName, userPass, login_ip, latency, time_shift,
				force_p2p, mcast_ip, mcast_force, mcast_port, type,
				uiport, analyze, mcast_ttl, id, mcast_out, complete,
				max_outputs, on, password, priority_ids ,testid });
		
		
		Assert.assertEquals(
				((BroadcasterPushInStreamCreationDriver) testDriver).testIMPL(
						userName, userPass, login_ip, latency, time_shift,
						force_p2p, mcast_ip, mcast_force, mcast_port, type,
						uiport, analyze, mcast_ttl, id, mcast_out, complete,
						max_outputs, on, password, priority_ids), "Stream " + "'" + id + "'"
						+ " added.");
	}
}
