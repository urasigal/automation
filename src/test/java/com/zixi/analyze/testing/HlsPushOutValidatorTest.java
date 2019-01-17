package com.zixi.analyze.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;
import com.zixi.drivers.drivers.HlsAppleAnalyzerDriver;
import com.zixi.tcpserver.JettyZixiRunnerThread;
import com.zixi.testing.BaseTestZixiMainComponents;

public class HlsPushOutValidatorTest extends BaseTestZixiMainComponents{
	@BeforeClass
	public void testInit() { testDriver = new HlsAppleAnalyzerDriver(); }

	@Parameters({"analyzer_url", "hls_url", "testid"})
	@Test
	public void HLS_PushOutputAppleAnalyzerTest(String analyzer_url, String hls_url, String testid) throws Exception {
		
		// Start embedded HTTP server - jetty (the server is the part of the test).
		JettyZixiRunnerThread jettyZixiRunnerThread = new JettyZixiRunnerThread();
		jettyZixiRunnerThread.startServers("src/main/resources");
		Thread.sleep(60_000);
		
		buildTestParametersString(new String[] { "analyzer_url", "hls_url", "testid" }, new String[] { analyzer_url, hls_url, testid });
		
		driverReslut = ((HlsAppleAnalyzerDriver) testDriver).testAnalyzer( analyzer_url,  hls_url);
		
		Assert.assertEquals(driverReslut.getResult(), "No errors");
	}
}