package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddUserDriver;
import com.zixi.drivers.drivers.ZenAddUserGroupDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddUserGroupTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddUserGroupDriver(); }

	@Parameters({"zen_userName", "zen_userPass", "login_ip", "uiport", "group_name", "testid"})
	@Test
	public void addUserZen(String zen_userName, String zen_userPass, String login_ip, String uiport,
		String group_name, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zen_userName", "zen_userPass", "login_ip", "uiport", "group_name", "testid"}, 
		new String[] {zen_userName, zen_userPass, login_ip, uiport, group_name, testid});
		
		driverReslut = ((ZenAddUserGroupDriver) testDriver).addZenUserGroup(zen_userName, zen_userPass, login_ip, uiport, group_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}