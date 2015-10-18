package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcaserSingleOutputStreamDeletionTest {

	private TestDriver testDriver;
	@BeforeClass
	public void testInit() {
		
			
			testDriver = new BroadcaserSingleOutputStreamDeletionDriver();
		
	}

	@Parameters({ "login_ip","userName","userPassword","id","uiport"})
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip,String userName,String userPassword,String id,String uiport) throws InterruptedException 
	{
		Assert.assertEquals(((BroadcaserSingleOutputStreamDeletionDriver) testDriver).testIMPL(login_ip, userName, userPassword, id, uiport), "Output " +  ((BroadcaserSingleOutputStreamDeletionDriver) testDriver).getId1() + " removed.");
	}
}
