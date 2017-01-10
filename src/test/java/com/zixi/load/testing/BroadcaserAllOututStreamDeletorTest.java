package com.zixi.load.testing;

import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserAllInputStreamDeletorDriver;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcaserAllOutputStreamDeletorDriver;
import com.zixi.testing.BaseTest;

public class BroadcaserAllOututStreamDeletorTest extends BaseTest{
	
	@Parameters({"testid"})
	@BeforeClass
	public void testInit(String testid) {
		testDriver = new BroadcaserAllOutputStreamDeletorDriver();
	}
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "testid" })
	@Test
	public void broadcasterDeleteAllOutputStreams(String login_ip, String userName, String userPassword, String uiport, String testid) throws Exception {
		
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
				
		testParameters = buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, testid });
		
		driverReslut = ((BroadcaserAllOutputStreamDeletorDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport);
		
		Assert.assertEquals(driverReslut.getResult(), "good");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
