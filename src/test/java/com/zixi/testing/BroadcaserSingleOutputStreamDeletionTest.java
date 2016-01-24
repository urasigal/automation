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

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

public class BroadcaserSingleOutputStreamDeletionTest extends BaseTest{
	
	private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() {
		
			
			testDriver = new BroadcaserSingleOutputStreamDeletionDriver();
		
	}

	@Parameters({ "login_ip","userName","userPassword","id","uiport", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip,String userName,String userPassword,String id,String uiport,String testid) throws InterruptedException 
	{
		this.testid = testid;
		Assert.assertEquals(((BroadcaserSingleOutputStreamDeletionDriver) testDriver).testIMPL(login_ip, userName, userPassword, id, uiport), "Output " +  ((BroadcaserSingleOutputStreamDeletionDriver) testDriver).getId1() + " removed.");
	}
	
}
