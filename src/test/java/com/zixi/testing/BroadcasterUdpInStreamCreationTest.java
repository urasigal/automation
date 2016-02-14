package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterUdpInStreamCreationTest extends BaseTest {

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterSingleUdpInCreationDriver();
	}

	
	@Parameters({ "userName", "userPass", "login_ip", "ts_port", "id",
			"rtp_type", "multi_src", "max_bitrate", "time_shift", "mcast_ip",
			"mcast_force", "mcast_port", "nic", "type", "multicast", "enc_key",
			"kompression", "uiport", "mcast_ttl", "enc_type", "mcast_out",
			"complete", "max_outputs", "on" ,"testid"})
	
	@Test
	public void broadcasterUdpInCreation(String userName, String userPass,
			String login_ip, String ts_port, String id, String rtp_type,
			String multi_src, String max_bitrate, String time_shift,
			String mcast_ip, String mcast_force, String mcast_port, String nic,
			String type, String multicast, String enc_key, String kompression,
			String uiport, String mcast_ttl, String enc_type, String mcast_out,
			String complete, String max_outputs, String on,String testid)
			throws InterruptedException {
		this.testid = testid;
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = testBaseFunction.buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "ts_port", "id",
				"rtp_type", "multi_src", "max_bitrate", "time_shift", "mcast_ip",
				"mcast_force", "mcast_port", "nic", "type", "multicast", "enc_key",
				"kompression", "uiport", "mcast_ttl", "enc_type", "mcast_out",
				"complete", "max_outputs", "on" ,"testid" }, 
				
				new String[] { userName, userPass, login_ip, ts_port, id,
				rtp_type, multi_src, max_bitrate, time_shift, mcast_ip,
				mcast_force, mcast_port, nic, type, multicast, enc_key,
				kompression, uiport, mcast_ttl, enc_type, mcast_out,
				complete, max_outputs, on ,testid });
		
		
		Assert.assertEquals(((BroadcasterSingleUdpInCreationDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, ts_port, id, rtp_type,
						multi_src, max_bitrate, time_shift, mcast_ip,
						mcast_force, mcast_port, nic, type, multicast, enc_key,
						kompression, uiport, mcast_ttl, enc_type, mcast_out,
						complete, max_outputs, on), "Stream " + "'" + id + "'"
				+ " added.");
	}
}
