package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class FeederOutputPushToBxTest {
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {

		testDriver = new FeederOutputPushToBxDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "name", "mip", "port",
			"ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
			"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force",
			"fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
			"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr",
			"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded","uiport" })
	@Test
	public void feederOutputToBxTest(String userName,
			String userPass, String login_ip, String name, String mip,
			String port, String ip, String prog, String chan, String type,
			String ostr, String oses, String oetp, String oeky, String obit,
			String olat, String ofc, String ocmp, String oold, String onfec,
			String fec_force, String fec_adaptive, String ofec, String ofecl,
			String stop_on_drop, String mmt, String smoothing, String limited,
			String minbps, String lim_enc_addr, String pad_to_cbr,
			String rtmp_feedback, String ohst, String oprt, String onic,
			String oalt,String bonded, String uiport) throws InterruptedException {
		
		Assert.assertEquals(((FeederOutputPushToBxDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, name, mip, port,
						ip, prog, chan, type, ostr, oses, oetp, oeky, obit,
						olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive,
						ofec, ofecl, stop_on_drop, mmt, smoothing, limited,
						minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst,
						oprt, onic, oalt,bonded, uiport),"Broadcaster output added.");
	}
}
