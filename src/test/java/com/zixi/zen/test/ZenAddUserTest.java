package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddUserDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddUserTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddUserDriver(); }

	@Parameters({"zen_userName", "zen_userPass", "login_ip", "uiport", "name", "password", "email", "is_admin", "testid"})
	@Test
	public void addUserZen(String zen_userName, String zen_userPass, String login_ip, String uiport,
		String name, String password, String email, String is_admin, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zen_userName", "zen_userPass", "login_ip", "uiport", "name", "password", "email", "is_admin", "testid"}, 
		new String[] {"zen_userName", "zen_userPass", "login_ip", "uiport", "name", "password", "email", "is_admin", "testid"});
		
		driverReslut = ((ZenAddUserDriver) testDriver).addZenUser();
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}