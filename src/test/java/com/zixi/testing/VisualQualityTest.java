package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

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
		
		Assert.assertEquals(((VisualQualityDriver) testDriver)
				.testVideo(), "pass");
	}
}
