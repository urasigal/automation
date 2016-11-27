package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ProxyLocalDriver;

public class ProxyLocalTest extends BaseTest{
	
	
	@BeforeClass
	public void testInit() {
			testDriver = new ProxyLocalDriver();
	}

	@Parameters({ "function", "source", "stream_name", "mode", "proxy_port", "regime", "testid"}) 
	@Test
	public void broadcasterSingleStreamRemoving(String function, String source, String stream_name, String mode, String proxy_port, String regime, String testid) throws InterruptedException 
	{
		this.testid = testid;
		Assert.assertEquals(((ProxyLocalDriver) testDriver).testIMPL(function, source, stream_name, mode, proxy_port, regime),
				"Output ");
	}
}
