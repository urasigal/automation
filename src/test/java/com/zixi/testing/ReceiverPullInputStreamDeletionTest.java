package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class ReceiverPullInputStreamDeletionTest {

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {

		testDriver = new ReceiverPullInputStreamDeletionDriver();
	}

	@Parameters({ "userName", "userPassword", "login_ip", "uiport", "id" })
	@Test
	public void feederOutputToBxTest(String userName, String userPassword,
			String login_ip, String uiport, String id)
			throws InterruptedException {
		String streamForDeletion = ((ReceiverPullInputStreamDeletionDriver) testDriver)
				.testIMPL(userName, userPassword, login_ip, uiport, id);
		Assert.assertEquals(streamForDeletion,
				"Stream removed.");
	}
}
