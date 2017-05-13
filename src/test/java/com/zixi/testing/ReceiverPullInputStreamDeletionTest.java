package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverPullInputStreamDeletionDriver;

public class ReceiverPullInputStreamDeletionTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new ReceiverPullInputStreamDeletionDriver();}

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id" ,"testid"})
	@Test
	public void feederOutputToBxTest(String userName, String userPassword, String login_ip, String uiport, String id,String testid)
	throws Exception {
		
		buildTestParametersString(new String[] { "userName", "userPassword", "login_ip", "uiport", "id" ,"testid" }, 
				
		new String[] { userName, userPassword, login_ip, uiport, id ,testid });
			
		// String streamForDeletion = ((ReceiverPullInputStreamDeletionDriver) testDriver).testIMPL(userName, userPassword, login_ip, uiport, id);
		
		driverReslut = ((ReceiverPullInputStreamDeletionDriver) testDriver).testIMPL(userName, userPassword, login_ip, uiport, id);
		Assert.assertEquals(driverReslut.getResult(),"Stream removed.");
	}
}
