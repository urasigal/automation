package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class ReceiverPullInputStreamDeletionTest extends BaseTest{

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {

		testDriver = new ReceiverPullInputStreamDeletionDriver();
	}

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id" ,"testid"})
	@Test
	public void feederOutputToBxTest(String userName, String userPassword,
			String login_ip, String uiport, String id,String testid)
			throws Exception {
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPassword", "login_ip", "uiport", "id" ,"testid" }, 
				
				new String[] { userName, userPassword, login_ip, uiport, id ,testid });
		
		
		String streamForDeletion = ((ReceiverPullInputStreamDeletionDriver) testDriver)
				.testIMPL(userName, userPassword, login_ip, uiport, id);
		Assert.assertEquals(streamForDeletion,
				"Stream removed.");
	}
}
