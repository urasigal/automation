package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcasterAddTranscoderProfileDriver;

public class BroadcasterAddIntelTranscoderProfileTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterAddTranscoderProfileDriver();
	}

	@Parameters({"userName", "userPass", "login_ip", "uiport", "mode", "profile_name", "enc", "bitrate", "gop", "fixed_gop", "closed_gop", "copy_gop",  
	"performance", "b_frames", "frame_type", "profile", "level", "bitrate_mode",
	"ref_frames", "hrd", "idr_int", "cavlc", "brightness", "contrast", "fps", "width", "height","crf", "tune", "keep_ar", "max_bitrate", "x264_two_pass", "testid" })
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String login_ip, String uiport,String mode, String profile_name,
	String enc, String bitrate, String gop, String fixed_gop, String closed_gop, String copy_gop, String performance,
	String b_frames, String frame_type, String profile, String level, String bitrate_mode,
	String ref_frames, String hrd, String idr_int, String cavlc, String brightness, String contrast, String fps,
	String width, String height, String crf, String tune, @Optional("0") String keep_ar,  String max_bitrate,  @Optional("0") String x264_two_pass,  @Optional("0") String max_qp  , @Optional("0") String bpp , String testid) throws Exception {

		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "mode", "profile_name",
		"enc", "bitrate", "gop", "fixed_gop", "closed_gop", "copy_gop",  "performance",
		"b_frames", "frame_type", "profile", "level", "bitrate_mode", "ref_frames", "hrd", "idr_int", "cavlc", "brightness", "contrast", "fps",
		"width", "height","crf", "tune", "keep_ar",  "max_bitrate", "x264_two_pass",  "max_qp",  "bpp", "testid"}, 
						
		new String[] {userName, userPass, login_ip, uiport, mode, profile_name, enc, bitrate, gop, fixed_gop, closed_gop, copy_gop, performance,
		b_frames, frame_type, profile, level, bitrate_mode, ref_frames, hrd, idr_int, cavlc, brightness, contrast, fps, width, height,crf, tune, keep_ar,  max_bitrate, x264_two_pass, max_qp,  bpp, testid});
		
		driverReslut = ((BroadcasterAddTranscoderProfileDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, mode, profile_name,
		enc, bitrate, gop, fixed_gop, closed_gop, copy_gop, performance, b_frames, frame_type, profile, level, bitrate_mode,
		ref_frames, hrd, idr_int, cavlc, brightness, contrast, fps, width, height,crf, tune, keep_ar, max_bitrate, x264_two_pass, max_qp,  bpp);
		
		Assert.assertEquals(driverReslut.getResult(), "Profile added, existing transcoded streams may be restarted.");
	}
}
