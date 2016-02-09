package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterAddTranscoderProfileTest extends BaseTest {

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterAddTranscoderProfileDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "profile_name",
			"enc", "bitrate", "gop", "fixed_gop", "closed_gop", "performance",
			"b_frames", "frame_type", "profile", "level", "bitrate_mode",
			"ref_frames", "idr_int", "cavlc", "brightness", "contrast", "fps",
			"width", "height", "max_bitrate", "testid" })
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName,
			String userPass, String login_ip, String uiport,
			String profile_name, String enc, String bitrate, String gop,
			String fixed_gop, String closed_gop, String performance,
			String b_frames, String frame_type, String profile, String level,
			String bitrate_mode, String ref_frames, String idr_int,
			String cavlc, String brightness, String contrast, String fps,
			String width, String height, String max_bitrate, String testid)
			throws InterruptedException {

		this.testid = testid;
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
				this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
				
				testParameters = buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "profile_name",
						"enc", "bitrate", "gop", "fixed_gop", "closed_gop", "performance",
						"b_frames", "frame_type", "profile", "level", "bitrate_mode",
						"ref_frames", "idr_int", "cavlc", "brightness", "contrast", "fps",
						"width", "height", "max_bitrate", "testid"  }, 
						
						new String[] {userName, userPass, login_ip, uiport, profile_name,
						enc, bitrate, gop, fixed_gop, closed_gop, performance,
						b_frames, frame_type, profile, level, bitrate_mode,
						ref_frames, idr_int, cavlc, brightness, contrast, fps,
						width, height, max_bitrate, testid });
		
		Assert.assertEquals(
				((BroadcasterAddTranscoderProfileDriver) testDriver)
						.testIMPL(userName, userPass, login_ip, uiport,
								profile_name, enc, bitrate, gop, fixed_gop,
								closed_gop, performance, b_frames, frame_type,
								profile, level, bitrate_mode, ref_frames,
								idr_int, cavlc, brightness, contrast, fps,
								width, height, max_bitrate), "Profile added, existing transcoded streams may be restarted.");
	}
}
