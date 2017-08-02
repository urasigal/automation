package com.zixi.g1050test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.zixi.maxwell.drivers.BroadcasterPushG1050ConfigurationDriver;

public class BroadcasterPushG1050ConfigurationTest{
	
	@Test
	public void confG1050OnMaxwell() {
		// Here in this point the parameters are hard coded.
		BroadcasterPushG1050ConfigurationDriver.confG1050PluginOnMaxwell();
		Assert.assertTrue(true);
	}
	
	@Test
	public void releaseG1050OnMaxwell() {
		// Here in this point the parameters are hard coded.
		BroadcasterPushG1050ConfigurationDriver.releaseG1050PluginOnMaxwell();
		Assert.assertTrue(true);
	}
}
