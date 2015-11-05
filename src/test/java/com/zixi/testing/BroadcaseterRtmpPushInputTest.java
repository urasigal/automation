package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterRtmpInCreationDriver;
import com.zixi.drivers.TestDriver;

public class BroadcaseterRtmpPushInputTest {
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterRtmpInCreationDriver();
	}

	@Parameters({ "userName","userPass","login_ip"})
	@Test
	public void broadcasterRtmpPullTest(String userName,String userPass,String login_ip) throws InterruptedException 
	{
		// Assert.assertEquals(((BroadcasterRtmpInCreationDriver) testDriver).testIMPL(userName, userPass, login_ip),"Stream " + "'"+ id +"'"+ " added.");
	}

}
