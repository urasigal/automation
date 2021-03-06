package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;

public class FFMPEGImageStatisticNegativeTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FFMPEGImageStatisticTestDriver(); }

	// The goal of the test is to measure a quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String testid) throws InterruptedException {
		
		buildTestParametersString(new String[] { "testid" }, new String[] { testid });
		
		driverReslut = ((FFMPEGImageStatisticTestDriver) testDriver).testStatistic(true).getResultObj();
		
		Assert.assertEquals(driverReslut.getResult(), "The number of sucessful attempts was 0");
	}
}
