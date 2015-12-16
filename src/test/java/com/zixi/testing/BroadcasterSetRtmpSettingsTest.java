package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

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
		Assert.assertEquals(((BroadcasterSetRtmpSettingsDriver) testDriver)
				.testIMPL(userName,
						 userPass,  login_ip, uiport, rtmp_on,  rtmp_port,
						 rtmp_auto_out,  rtmp_auto_in,  rtmp_pcr_int,
						 rtmp_auto_out_latency), "1");
	}
}
