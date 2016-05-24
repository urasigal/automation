package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterPushInStreamCreationTest extends BaseTest {
	
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPushInStreamCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "latency", "time_shift",
			"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
			"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
			"max_outputs", "on", "password" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String login_ip, String latency,
			String time_shift, String force_p2p, String mcast_ip,
			String mcast_force, String mcast_port, String type, String uiport,
			String analyze, String mcast_ttl, String id, String mcast_out,
			String complete, String max_outputs, String on, String password,
			String testid) throws InterruptedException {
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		pid = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "latency", "time_shift",
				"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type",
				"uiport", "analyze", "mcast_ttl", "id", "mcast_out", "complete",
				"max_outputs", "on", "password" ,"testid"}, 
				
				new String[] {userName, userPass, login_ip, latency, time_shift,
				force_p2p, mcast_ip, mcast_force, mcast_port, type,
				uiport, analyze, mcast_ttl, id, mcast_out, complete,
				max_outputs, on, password ,testid });
		
		
		Assert.assertEquals(
				((BroadcasterPushInStreamCreationDriver) testDriver).testIMPL(
						userName, userPass, login_ip, latency, time_shift,
						force_p2p, mcast_ip, mcast_force, mcast_port, type,
						uiport, analyze, mcast_ttl, id, mcast_out, complete,
						max_outputs, on, password), "Stream " + "'" + id + "'"
						+ " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(pid, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
