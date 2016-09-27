package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.FFMPEGImageStatisticTestDriver;

public class FFMPEGBondingBackupTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {

		// This is a test driver.
		testDriver = new FFMPEGImageStatisticTestDriver(testFlowDescriptor);
	}

	// The goal of the test is to measure quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"testid"})
	@Test
	public void ffmpegBondingTestBackUpScenarioPartialLimitation(String testid) throws InterruptedException {
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] { "testid" }, new String[] {"testid" });
		
		Assert.assertEquals(((FFMPEGImageStatisticTestDriver) testDriver).backUpBondedPartialLimitation(), "good");
	}
	
	
	// The goal of the test is to measure quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"testid"})
	@Test
	public void ffmpegBondingTestBackUpScenarioFullLimitation(String testid) throws InterruptedException {
		
		testFlowDescriptor.append("\nStarting the test FFMPEGBondingBackupTest.ffmpegBondingTestBackUpScenarioFullLimitation" );
		
		testParameters = buildTestParametersString(new String[] { "testid" }, new String[] {"testid" }); // Fill out test parameters.
		
		Assert.assertEquals(((FFMPEGImageStatisticTestDriver) testDriver).backUpBondedFullLimitation(), "good");
	}
}