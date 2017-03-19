package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverInputStatisticDriver;

public class ReceiverInputStatisticTest extends BaseTest {
	
	@BeforeClass
	public void testInit() { testDriver = new ReceiverInputStatisticDriver(); }

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id", "testduration" ,"testid"})
	@Test
	public void receiverInputStatistic(String userName, String userPassword, String login_ip, String uiport, String id, String testduration, String testid) throws Exception {
		
		buildTestParametersString(new String[] { "userName", "userPassword", "login_ip", "uiport", "id", "testduration" ,"testid" }, 
				
		new String[] {userName, userPassword, login_ip, uiport, id, testduration ,testid });
		
		driverReslut = ((ReceiverInputStatisticDriver) testDriver) .testIMPL(userName, userPassword, login_ip, uiport, id, testduration);
		
		Assert.assertEquals(driverReslut.getResult(), "tested");
	}
}
