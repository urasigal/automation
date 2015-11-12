package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.BroadcasterRtmpInCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.tools.TestlinkIntegration;

public class BroadcaseterRtmpPushInputTest extends BaseTest{
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterRtmpInCreationDriver();
	}

	@Parameters({ "userName","userPass","login_ip" ,"testid"})
	@Test
	public void broadcasterRtmpPullTest(String userName,String userPass,String login_ip ,String testid) throws InterruptedException 
	{
		// Assert.assertEquals(((BroadcasterRtmpInCreationDriver) testDriver).testIMPL(userName, userPass, login_ip),"Stream " + "'"+ id +"'"+ " added.");
	}
	
}
