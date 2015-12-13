package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterCreateAddaptiveGroupDriver;

public class BroadcasterGenericTest extends BaseTest{

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterCreateAddaptiveGroupDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name",
			"record", "zixi", "hls", "hds", "mpd", "mmt", "compress_zixi",
			"multicast", "streams", "bitrates", "max_time", "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass,
			String login_ip, String uiport, String name, String record,
			String zixi, String hls, String hds, String mpd, String mmt,
			String compress_zixi, String multicast, String streams,
			String bitrates, String max_time, String testid)
			throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(
				((BroadcasterCreateAddaptiveGroupDriver) testDriver).testIMPL(
						userName, userPass, login_ip, uiport, name, record,
						zixi, hls, hds, mpd, mmt, compress_zixi, multicast,
						streams, bitrates, max_time), "{\"success\":1}");
	}
	
}
