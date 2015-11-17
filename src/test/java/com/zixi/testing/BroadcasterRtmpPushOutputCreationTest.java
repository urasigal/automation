package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcasterRtmpPushOutputCreationTest extends BaseTest {

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterRtmpPushOutputCreationDriver();
	}

	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "type",
			"name", "stream", "matrix", "url", "url_alt", "rtmp_stream",
			"user", "bandwidth", "latency", "reconnect", "sendfi",
			"disconnect_low_br", "static_latency", "dec_type", "dec_key",
			"password", "testid" })
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip,
			String userName, String userPassword, String uiport, String type,
			String name, String stream, String matrix, String url,
			String url_alt, String rtmp_stream, String user, String bandwidth,
			String latency, String reconnect, String sendfi,
			String disconnect_low_br, String static_latency, String dec_type,
			String dec_key, String password, String testid)
			throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(
				((BroadcasterRtmpPushOutputCreationDriver) testDriver)
						.testIMPL(login_ip, userName, userPassword, uiport,
								type, name, stream, matrix, url, url_alt,
								rtmp_stream, user, bandwidth, latency,
								reconnect, sendfi, disconnect_low_br,
								static_latency, dec_type, dec_key, password),
				"Output " +name+ " added.");
	}
}
