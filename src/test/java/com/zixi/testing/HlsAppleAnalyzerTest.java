package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.HlsAppleAnalyzerDriver;

public class HlsAppleAnalyzerTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new HlsAppleAnalyzerDriver(); }

	@Parameters({"analyzer_url", "hls_url", "testid"})
	@Test
	public void testHLSagainsAppleStreamValidatorTool(String analyzer_url, String hls_url, String testid) throws Exception {
		
		buildTestParametersString(new String[] { "analyzer_url", "hls_url", "testid" }, new String[] { analyzer_url, hls_url, testid });
		
		driverReslut = ((HlsAppleAnalyzerDriver) testDriver).testAnalyzer( analyzer_url,  hls_url);
		
		Assert.assertEquals(driverReslut.getResult(), "No errors");
	}
}