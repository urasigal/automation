package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.FFMPEGImageStatisticTestDriver;

public class FFMPEGImageStatisticNegativeTest extends BaseTest{
	@BeforeClass
	public void testInit() {

		// This is a test driver.
		testDriver = new FFMPEGImageStatisticTestDriver();
	}

	// The goal of the test is to measure a quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String testid) throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(
				((FFMPEGImageStatisticTestDriver) testDriver)
						.testStatistic(true), "good");
	}

}
