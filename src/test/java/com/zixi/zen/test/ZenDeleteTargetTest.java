package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ZenDeleteChannelDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenDeleteTargetTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenDeleteChannelDriver(); }

	// Delete channel.
	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "target_type", "target_name", "testid"})
	@Test
	public void deleteTarget(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String target_type, String target_name, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "target_type", "target_name", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, target_type, target_name, testid});
		
		driverReslut = ((ZenDeleteChannelDriver) testDriver).deleteTarget(zenUser, zenPass, zenLogin_ip, zenUiPort, target_type, target_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}