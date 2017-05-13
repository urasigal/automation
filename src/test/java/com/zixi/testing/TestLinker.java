package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ProductAboutDriver;
import com.zixi.drivers.drivers.TestDriver;
import com.zixi.drivers.drivers.TestLinkerDriver;

public class TestLinker extends BaseTestZixiMainComponents{
		
	@BeforeClass
	public void testInit() {  testDriver = new TestLinkerDriver();}
	
		@Parameters({"userName", "userPass", "login_ip", "uiport"})
		@Test
		public void setToBlockedStatusTestLinkedTests(String userName, String userPass, String login_ip, String uiport) throws Exception 
		{
			ProductAboutDriver productAboutDriver = new ProductAboutDriver();
	
			String version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
			
			driverReslut = ((TestLinkerDriver) testDriver).testIMPL(version);
			
			Assert.assertEquals(driverReslut.getResult(), "success");
		}
}
