package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenDeleteClusterDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenDeleteUserTest extends BaseTestZixiMainComponentsZen {
	
	@BeforeClass
	public void testInit() { testDriver = new ZenDeleteClusterDriver(); }

	// Test parameters - these parameters will be provided by appropriate suite's XML file.
	@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "userName", "testid"})
	@Test
	public void deleteUserFromZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
	String userName, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "userName", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, userName, testid});
		
		driverReslut = ((ZenDeleteClusterDriver) testDriver).deleteUser(zenUserName, zenUserPass, zenLogin_ip, zenUiport, userName);
		Assert.assertEquals(driverReslut.getResult(), "{\"success\":true}"); 
	}
}