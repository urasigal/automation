package com.zixi.analyze.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;
import com.zixi.tcpserver.JettyZixiRunnerThread;
import com.zixi.testing.BaseTestZixiMainComponents;

public class FFMPEGImageStatisticHlsPushOutTest extends BaseTestZixiMainComponents{
	@BeforeClass
	public void testInit() {testDriver = new FFMPEGImageStatisticTestDriver(); }

	// The goal of the test is to measure a quality of a Zixi delivered video by using FFMPEG.
	// The quality is estimated by a number of a stream probing (FFMPEG) and then getting a ratio between a successful probing to failed attempts.
	@Parameters({"mode", "testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String mode, String testid) throws Exception {
		
		// Start locally HTTP server.
		JettyZixiRunnerThread jettyZixiRunnerThread = new JettyZixiRunnerThread();
		jettyZixiRunnerThread.startServers("src/main/resources");
		Thread.sleep(60_000);
		
		buildTestParametersString(new String[] { "mode", "testid" }, new String[] { mode, testid});
		
		driverReslut = ((FFMPEGImageStatisticTestDriver) testDriver).testStatistic(mode);
		
		jettyZixiRunnerThread.stopServers();
		
		Assert.assertEquals(driverReslut.getResult(), "good");
	}
}

