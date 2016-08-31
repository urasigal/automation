package com.zixi.load.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.FeederOutputDeletionDriver;
import com.zixi.load.drivers.FeederBroadcasterBondingMultipleThreeLinkDriver;
import com.zixi.load.drivers.FeederMultipleDeletionDriver;
import com.zixi.testing.BaseTest;

public class FeederMultipleDeletionTest  extends BaseTest{
	
	//The method will be run before the first test's method invocation in the current class.
	@Parameters({"testid"})
	@BeforeClass
	public void testInit(String testid) {
		this.testid = testid;
		testDriver = new FeederMultipleDeletionDriver();
	}
	
	
	
	@Parameters({ "userName_feeder", "userPass_feeder", "login_ip_feeder", "uiport", "mip",  "port", "ip", "prog", "chan", "type"})
	
	// This test will create a number of bonded output streams from feeder to broadcaster.
	@Test
	public void broadcasterMultiplePullInCreation(String userName_feeder, String userPass_feeder, String login_ip_feeder, String uiport, 
			String mip, String port, String ip, String prog, String chan, String type) throws Exception {
		
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip_feeder, uiport, userName_feeder, userPass_feeder);
		
		// Here we take PIDs from a two broadcaster servers because of in this particular test case a two different broadcasters are involved.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_feeder,  "22",  "pidof zixi_feeder");
		
		// Gather the test parameters in order to pass them to the TestLink
		testParameters = buildTestParametersString(new String[] { "userName_feeder", "userPass_feeder", "login_ip_feeder", "uiport", "mip",  "port", "ip", "prog", "chan", "type"}, 
				
		 new String[] { userName_feeder, userPass_feeder, login_ip_feeder, uiport, mip, port, ip, prog, chan, type });
		
		// Actual test method.
		Assert.assertEquals(((FeederMultipleDeletionDriver) testDriver).testIMPL
		(userName_feeder, userPass_feeder, login_ip_feeder, uiport, mip, port, ip, prog, chan, type), "pass");
		
		// Checking if broadcaster has crashes while execution of the test.
		// This is special case because of here a two broadcaster are involved.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid ("root",  "zixiroot1234",  login_ip_feeder,  "22",  "pidof zixi_feeder"));
	} 
}
