package com.zixi.setup;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ProductAboutDriver;
import com.zixi.testing.BaseTestZixiMainComponents;
import com.zixi.zapi.ZapiCycleIntegrator;

public class ZapiAutomationSetup extends BaseTestZixiMainComponents{
	@BeforeClass
	public void testInit() {  testDriver = new ZapiCycleIntegrator();}
	
		@Parameters({ "userName", "userPass", "login_ip", "uiport", "expand", "clonedCycleId", "cycleName", "environment", 
		"description", "startDate", "endDate", "projectId", "versionId", "zapiUser", "zapiAccesskey", "zapiSecretkey"})
		@Test
		public void setTestCycle(String userName, String userPass, String login_ip, String uiport, String expand, String clonedCycleId, String cycleName, String environment, 
		String description, String startDate, String endDate, String projectId, String versionId, String zapiUser, String zapiAccesskey,
		String zapiSecretkey) throws Exception 
		{
			ProductAboutDriver productAboutDriver = new ProductAboutDriver();
			String version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
			driverReslut = ((ZapiCycleIntegrator) testDriver).addTestCycleSetup(version, expand, clonedCycleId, cycleName, environment, 
			description, startDate, endDate, projectId, versionId, zapiUser, zapiAccesskey, zapiSecretkey);
			
			Assert.assertEquals(driverReslut.getResult(), "success");
		}
}
