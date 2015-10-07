package com.zixi.testing;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterSingleInputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;

public class BroadcasterSingleInputStreamDeletionTest {
	
	private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() {
		// Load the page in the browser
		testDriver = new BroadcasterSingleInputStreamDeletionDriver();
	}

	@Parameters({"login_ip","userName","userPassword","streamId","uiport"})
	@Test
	public void broadcasterInputStreamDeletion(String login_ip, String userName, String userPassword, String streamId, String uiport) throws InterruptedException {
		Assert.assertNotNull(((BroadcasterSingleInputStreamDeletionDriver)testDriver).removeInput(login_ip, userName, userPassword, streamId, uiport));
	}
}