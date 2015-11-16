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


// The goal of the test is to verify the broadcaster RTMP Pull input connection.
// The broadcaster RTMP Pull input connection is defined as an ability of a broadcaster server to establish a RTMP connection with 
// an another broadcaster when the RTMP connection initiator will be the receiving broadcaster server.
public class BroadcasterRtmpInputTest extends BaseTest {
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterRtmpInCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "rtmp_nulls", "id",
			"rtmp_url", "rtmp_name", "time_shift", "mcast_ip", "mcast_force",
			"mcast_port", "type", "rtmp_user", "rtmp_bitrate", "rtmp_passwd",
			"uiport", "mcast_ttl", "rtmp_latency", "mcast_out", "complete",
			"max_outputs", "on" ,"testid"})
	@Test
	public void broadcasterRtmpPullTest(String userName, String userPass,
			String login_ip, String rtmp_nulls, String id, String rtmp_url,
			String rtmp_name, String time_shift, String mcast_ip,
			String mcast_force, String mcast_port, String type,
			String rtmp_user, String rtmp_bitrate, String rtmp_passwd,
			String uiport, String mcast_ttl, String rtmp_latency,
			String mcast_out, String complete, String max_outputs, String on,
			String testid) throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(((BroadcasterRtmpInCreationDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, rtmp_nulls, id,
						rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force,
						mcast_port, type, rtmp_user, rtmp_bitrate, rtmp_passwd,
						uiport, mcast_ttl, rtmp_latency, mcast_out, complete,
						max_outputs, on), "Stream " + "'" + id + "'"
				+ " added.");
	}

}
