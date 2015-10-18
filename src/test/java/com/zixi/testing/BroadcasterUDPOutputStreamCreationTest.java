package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcasterUDPOutputStreamCreationTest {
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterUdpOutputCreationDriver();
	}

	@Parameters({ "userName", "userPass", "loin_ip", "port", "stream",
			"streamname", "host", "id", "rtp", "fec", "smoothing", "ttl",
			"remux_bitrate", "df", "local_port", "dec_key", "type", "rows",
			"remux_buff", "local_ip", "remux_restampdts", "uiport",
			"remux_pcr", "dec_type", "cols" })
	@Test
	public void broadcasterPullInCreation(String userName, String userPass,
			String loin_ip, String port, String stream, String streamname,
			String host, String id, String rtp, String fec, String smoothing,
			String ttl, String remux_bitrate, String df, String local_port,
			String dec_key, String type, String rows, String remux_buff,
			String local_ip, String remux_restampdts, String uiport,
			String remux_pcr, String dec_type, String cols)
			throws InterruptedException {
		Assert.assertNotNull(((BroadcasterUdpOutputCreationDriver) testDriver)
				.testIMPL(userName, userPass, loin_ip, port, stream,
						streamname, host, id, rtp, fec, smoothing, ttl,
						remux_bitrate, df, local_port, dec_key, type, rows,
						remux_buff, local_ip, remux_restampdts, uiport,
						remux_pcr, dec_type, cols));
	}
}
