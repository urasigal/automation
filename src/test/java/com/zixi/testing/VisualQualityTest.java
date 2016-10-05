package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.drivers.VisualQualityDriver;

public class VisualQualityTest extends BaseTest{
	
	
	@BeforeClass
	public void testInit() {
		testDriver = new VisualQualityDriver();
	}

	@Parameters({"testid"})
	@Test
	public void broadcasterRtmpPullTest(String testid) throws InterruptedException {
		this.testid = testid;
		
		
		testParameters = buildTestParametersString(new String[] {"testid"}, 
		new String[] {testid});
		
		String testResult = ((VisualQualityDriver) testDriver).testVideo();
		String segments[] = testResult.split("@");
		manulDescription = 	segments[1];
		Assert.assertEquals(segments[0], "pass");
	}
}
