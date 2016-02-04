package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class ProxyLocalTest extends BaseTest{
	
private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() {
			testDriver = new ProxyLocalDriver();
	}

	@Parameters({ "function" 		// Proxy functionality,
				   ,"source" 		// Source IP address.
				   ,"stream_name" 	// Source stream name.
				   ,"mode" 			// Proxy functional mode.
				   , "proxy_port"	// The port that proxy listen to 
				   , "testid"
			}) 
	@Test
	public void broadcasterSingleStreamRemoving(String function, String source, String stream_name, String mode, String proxy_port, String testid) throws InterruptedException 
	{
		this.testid = testid;
		Assert.assertEquals(((ProxyLocalDriver) testDriver).testIMPL(function, source, stream_name, mode, proxy_port),
				"Output ");
	}
}
