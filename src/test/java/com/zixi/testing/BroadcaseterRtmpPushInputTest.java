package com.zixi.testing;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterRtmpInCreationDriver;
import com.zixi.drivers.drivers.TestDriver;

public class BroadcaseterRtmpPushInputTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {	testDriver = new BroadcasterRtmpInCreationDriver(); }

	@Parameters({ "userName","userPass","login_ip" ,"testid"})
	@Test
	public void broadcasterRtmpPullTest(String userName,String userPass,String login_ip ,String testid) throws InterruptedException 
	{
		// Assert.assertEquals(((BroadcasterRtmpInCreationDriver) testDriver).testIMPL(userName, userPass, login_ip),"Stream " + "'"+ id +"'"+ " added.");
	}
	
}
