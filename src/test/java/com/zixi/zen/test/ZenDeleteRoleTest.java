package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenDeleteClusterDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenDeleteRoleTest extends BaseTestZixiMainComponentsZen {
	
	@BeforeClass
	public void testInit() { testDriver = new ZenDeleteClusterDriver(); }

	// Test parameters - these parameters will be provided by appropriate suite's XML file.
	@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "roleName", "testid"})
	@Test
	public void deleteRoleFromZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
	String roleName, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "roleName", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, roleName, testid});
		
		driverReslut = ((ZenDeleteClusterDriver) testDriver).deleteRole(zenUserName, zenUserPass, zenLogin_ip, zenUiport, roleName);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}