package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddTranscoderProfileDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddTranscoderProfileTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddTranscoderProfileDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "audio_bitrate", "audio_encoder_profile", "bitrate_avg", "bitrate_max", "b_frames",
	"do_audio", "do_video", "encoding_profile", "fps", "gop", "gop_closed", "gop_fixed", "hardware_accel",
	"hdr_buff_length", "height", "interlaced", "keep_audio", "keep_video", "performance", "profile", "ref_frames", "width", "testid"})
	@Test
	public void addFeederSourceToZen(String zenUser, String zenPass, String zenLogin_ip, 
	String zenUiPort, String audio_bitrate, String audio_encoder_profile, String bitrate_avg, String bitrate_max, String b_frames,
	String do_audio, String do_video, String encoding_profile, String fps, String gop, String gop_closed, String gop_fixed, String hardware_accel,
	String hdr_buff_length, String height, String interlaced, String keep_audio, String keep_video, String performance, String profile,
	String ref_frames, String width, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", 
		"zenUiPort", "audio_bitrate", "audio_encoder_profile", "bitrate_avg", "bitrate_max", "b_frames",
		"do_audio", "do_video", "encoding_profile", "fps", "gop", "gop_closed", "gop_fixed", "hardware_accel",
		"hdr_buff_length", "height", "interlaced", "keep_audio", "keep_video", "performance", "profile", "ref_frames", "width", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, 
		zenUiPort, audio_bitrate, audio_encoder_profile, bitrate_avg, bitrate_max, b_frames,
		do_audio, do_video, encoding_profile, fps, gop, gop_closed, gop_fixed, hardware_accel,
		hdr_buff_length, height, interlaced, keep_audio, keep_video, performance, profile, ref_frames, width, testid});
		
		driverReslut = ((ZenAddTranscoderProfileDriver) testDriver).addTranscoderProfiles(zenUser, zenPass, zenLogin_ip, 
		zenUiPort, audio_bitrate, audio_encoder_profile, bitrate_avg, bitrate_max, b_frames,
		do_audio, do_video, encoding_profile, fps, gop, gop_closed, gop_fixed, hardware_accel,
		hdr_buff_length, height, interlaced, keep_audio, keep_video, performance, profile, ref_frames, width);
		
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}