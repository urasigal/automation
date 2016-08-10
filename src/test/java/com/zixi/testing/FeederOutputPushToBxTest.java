package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class FeederOutputPushToBxTest extends BaseTest{
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
			"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded","uiport" ,"testid"})
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
			String oalt,String bonded, String uiport,String testid) throws Exception {
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port",
				"ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
				"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force",
				"fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
				"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr",
				"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded","uiport" ,"testid"}, 
				
				new String[] { userName, userPass, login_ip, name, mip, port,
				ip, prog, chan, type, ostr, oses, oetp, oeky,
				obit, olat, ofc, ocmp, oold, onfec, fec_force,
				fec_adaptive, ofec, ofecl, stop_on_drop, mmt,
				smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,
				rtmp_feedback, ohst, oprt, onic, oalt,bonded,uiport ,testid });
		
		
		Assert.assertEquals(((FeederOutputPushToBxDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, name, mip, port,
						ip, prog, chan, type, ostr, oses, oetp, oeky, obit,
						olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive,
						ofec, ofecl, stop_on_drop, mmt, smoothing, limited,
						minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst,
						oprt, onic, oalt,bonded, uiport),"Broadcaster output added.");
	} // End of test.
	
	@Parameters({ "userName", "userPass", "login_ip", "name", "mip", "port",
		"ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
		"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force",
		"fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
		"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr",
		"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded", "rtmp_stream",
		"rtmp_url", "rtmp_user", "rtmp_pass", "rtmp_url2", "rtmp_hot","uiport" ,"testid"})
@Test
public void feederOutputToBxTestAutomaticRtmp(String userName,
		String userPass, String login_ip, String name, String mip,
		String port, String ip, String prog, String chan, String type,
		String ostr, String oses, String oetp, String oeky, String obit,
		String olat, String ofc, String ocmp, String oold, String onfec,
		String fec_force, String fec_adaptive, String ofec, String ofecl,
		String stop_on_drop, String mmt, String smoothing, String limited,
		String minbps, String lim_enc_addr, String pad_to_cbr,
		String rtmp_feedback, String ohst, String oprt, String onic,
		String oalt,String bonded, String rtmp_stream,String rtmp_url ,
		String rtmp_user,String rtmp_pass, String rtmp_url2, String rtmp_hot, 
		String uiport,String testid) throws Exception {
	this.testid = testid;
	
	testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port",
			"ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
			"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force",
			"fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
			"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr",
			"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded", "rtmp_stream",
			"rtmp_url", "rtmp_user", "rtmp_pass", "rtmp_url2", "rtmp_hot","uiport" ,"testid"}, 
			
			new String[] { userName, userPass, login_ip, name, mip, port,
			ip, prog, chan, type, ostr, oses, oetp, oeky,
			obit, olat, ofc, ocmp, oold, onfec, fec_force,
			fec_adaptive, ofec, ofecl, stop_on_drop, mmt,
			smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,
			rtmp_feedback, ohst, oprt, onic, oalt,bonded, rtmp_stream,
			rtmp_url, rtmp_user, rtmp_pass, rtmp_url2, rtmp_hot ,uiport ,testid });
	
	
	Assert.assertEquals(((FeederOutputPushToBxDriver) testDriver)
			.testIMPL(userName, userPass, login_ip, name, mip, port,
					ip, prog, chan, type, ostr, oses, oetp, oeky, obit,
					olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive,
					ofec, ofecl, stop_on_drop, mmt, smoothing, limited,
					minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst,
					oprt, onic, oalt,bonded,rtmp_stream, rtmp_url, rtmp_user, 
					rtmp_pass, rtmp_url2, rtmp_hot, uiport),"Broadcaster output added.");
} // End of test.
	
}
