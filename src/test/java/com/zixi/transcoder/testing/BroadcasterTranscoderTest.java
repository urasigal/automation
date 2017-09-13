package com.zixi.transcoder.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;
import com.zixi.drivers.drivers.FfmpegStreamParametersDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterTranscoderTest  extends BaseTestZixiMainComponents{
	

	// The goal of the test is to measure quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probes (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"codec_name", "profile", "coded_width", "coded_height", "field_order", "testid"})
	@Test
	public void broadcasterTranscoderTestVideoStreamParametersByFfmpeg(String codec_name, String profile, String coded_width, String coded_height, String field_order, String testid) throws InterruptedException {
		
		FfmpegStreamParametersDriver ffmpegStreamParametersDriver = new FfmpegStreamParametersDriver("10.7.0.68", "9999");
		
		buildTestParametersString(new String[] { "codec_name", "profile", "coded_width", "coded_height", "field_order", "testid" }, 
		new String[] { codec_name, profile, coded_width, coded_height, field_order, testid });
		
		driverReslut = ((FFMPEGImageStatisticTestDriver) testDriver).testStatistic().getResultObj();
		
		Assert.assertEquals(driverReslut.getResult(), "codec_name " + codec_name + " profile " + profile + " coded_width " + coded_width + " coded_height " + coded_height + " field_order " + field_order);
	}
}
