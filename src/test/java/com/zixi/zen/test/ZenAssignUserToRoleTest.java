package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAssignUserToRoleDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAssignUserToRoleTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAssignUserToRoleDriver(); }

	@Parameters({"zen_userName", "zen_userPass", "login_ip", "uiport", "role_name", "user_name", "testid"})
	@Test
	public void addUserGroupZen(String zen_userName, String zen_userPass, String login_ip, String uiport, String role_name,
	String user_name, String testid) throws Exception { 
		
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zen_userName", "zen_userPass", "login_ip", "uiport", "role_name", "user_name", "testid"}, 
		new String[] {zen_userName, zen_userPass, login_ip, uiport, role_name, user_name, testid});
		
		driverReslut = ((ZenAssignUserToRoleDriver) testDriver).assignUserToRole(zen_userName, zen_userPass, login_ip, uiport, role_name, user_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}