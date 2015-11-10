package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class ReceiverInputStatisticTest {
	
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() {

		testDriver = new ReceiverInputStatisticDriver();
	}

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id", "testduration"})
	@Test
	public void receiverInputStatistic(String userName, String userPassword,
			String login_ip, String uiport, String id, String testduration)
			throws InterruptedException {
		String streamForDeletion = ((ReceiverInputStatisticDriver) testDriver)
				.testIMPL(userName, userPassword, login_ip, uiport, id, testduration);
		Assert.assertEquals(streamForDeletion,
				"tested");
	}

}
