package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterSetRtmpSettingsTest extends BaseTest {
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterSetRtmpSettingsDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "rtmp_on", "rtmp_port",
			"rtmp_auto_out", "rtmp_auto_in", "rtmp_pcr_int",
			"rtmp_auto_out_latency", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String login_ip, String uiport, String rtmp_on, String rtmp_port,
			String rtmp_auto_out, String rtmp_auto_in, String rtmp_pcr_int,
			String rtmp_auto_out_latency, String testid)
			throws InterruptedException {
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		pid = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "rtmp_on", "rtmp_port",
				"rtmp_auto_out", "rtmp_auto_in", "rtmp_pcr_int",
				"rtmp_auto_out_latency", "testid" }, 
				
				new String[] { userName, userPass, login_ip, uiport, rtmp_on, rtmp_port,
				rtmp_auto_out, rtmp_auto_in, rtmp_pcr_int,
				rtmp_auto_out_latency, testid });
		
		
		Assert.assertEquals(((BroadcasterSetRtmpSettingsDriver) testDriver)
				.testIMPL(userName,
						 userPass,  login_ip, uiport, rtmp_on,  rtmp_port,
						 rtmp_auto_out,  rtmp_auto_in,  rtmp_pcr_int,
						 rtmp_auto_out_latency), "1");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(pid, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
