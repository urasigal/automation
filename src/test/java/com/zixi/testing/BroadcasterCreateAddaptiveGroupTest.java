package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterCreateAddaptiveGroupDriver;

public class BroadcasterCreateAddaptiveGroupTest extends BaseTest {

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
			throws Exception {
		this.testid = testid;
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
				this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
				testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name",
						"record", "zixi", "hls", "hds", "mpd", "mmt", "compress_zixi",
						"multicast", "streams", "bitrates", "max_time", "testid" }, 
						
						new String[] { "userName", "userPass", "login_ip", "uiport", "name",
						"record", "zixi", "hls", "hds", "mpd", "mmt", "compress_zixi",
						"multicast", "streams", "bitrates", "max_time", "testid" });
		
		Assert.assertEquals(
				((BroadcasterCreateAddaptiveGroupDriver) testDriver).testIMPL(
						userName, userPass, login_ip, uiport, name, record,
						zixi, hls, hds, mpd, mmt, compress_zixi, multicast,
						streams, bitrates, max_time), "{\"success\":1}");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}

}
