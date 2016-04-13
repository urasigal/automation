package com.zixi.tools;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;
import com.zixi.testing.BaseTest;

public class TestLinker extends BaseTest{
private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() { 
		testDriver = new TestLinkerDriver();
	}

	@Parameters({})
	@Test
	public void setToBlockedStatusTestLinkedTests() throws InterruptedException 
	{
		Assert.assertEquals(((TestLinkerDriver) testDriver).testIMPL(),
				"success");
	}
}
