package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;

public class FFMPEGImageStatisticModeTest extends BaseTest{

	
	@BeforeClass
	public void testInit() {

		// This is a test driver.
		testDriver = new FFMPEGImageStatisticTestDriver();
	}

	// The goal of the test is to measure a quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"mode", "testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String mode, String testid) throws InterruptedException {
		
		testParameters = buildTestParametersString(new String[] { "mode", "testid" }, 
				
				new String[] { mode, testid});
		
		
		Assert.assertEquals(((FFMPEGImageStatisticTestDriver) testDriver).testStatistic(mode), "good");
	}
}
