package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class FeederOutputDeletioinTest {

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new FeederOutputDeletionDriver();
	}

	@Parameters({ "userName", "userPass", "loin_ip", "uiport", "id", "mip",
			"port", "ip", "prog", "chan", "type","host" })
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String loin_ip, String uiport, String id,
			String mip, String port, String ip, String prog, String chan,
			String type, String host) throws InterruptedException {
		Assert.assertEquals(((FeederOutputDeletionDriver) testDriver).testIMPL(
				userName, userPass, loin_ip, uiport, id, mip, port, ip, prog,
				chan, type, host), "Output deleted.");
	}
}
