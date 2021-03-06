package com.zixi.analyze.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserAllInputStreamDeletorDriver;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSwitchInputsFaioverNormalDistributionDriver;
import com.zixi.drivers.drivers.BroadcasterUdpOutputCreationDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterNrmlDistSwitchFailoverTest extends BaseTestZixiMainComponents
{	
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSwitchInputsFaioverNormalDistributionDriver(); }
	
	@Parameters({ "remoteBX_login_ip", "remoteBXuserName", "remoteBXuserPassword", "remoteBXuiport",
	"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "testid" })
	@Test
	public void broadcasterSwitchInputsFaioverNormalDistibutionTest(String remoteBX_login_ip, String remoteBXuserName, String remoteBXuserPassword, String remoteBXuiport,
    String login_ip, String userName, String userPassword, String uiport, String testElapsedTime, String streamSwitchInterval, String streamReferenceName, String testid) throws Exception {
		
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		
		buildTestParametersString(new String[] {"remoteBX_login_ip", "remoteBXuserName", "remoteBXuserPassword", "remoteBXuiport",
		"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "testid"}, 
		new String[] {remoteBX_login_ip, remoteBXuserName, remoteBXuserPassword, remoteBXuiport,
		login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval,streamReferenceName,  testid});
		
		driverReslut = ((BroadcasterSwitchInputsFaioverNormalDistributionDriver) testDriver).testNormalDistributioInputSwitching(remoteBX_login_ip, remoteBXuserName, 
		remoteBXuserPassword, remoteBXuiport, login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval, streamReferenceName);	
		Assert.assertEquals(driverReslut.getResult(), "passed");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
	@Parameters({ "remoteBX_login_ip",  "remoteBXuserName",  "remoteBXuserPassword",  "remoteBXuiport",
	"testElapsedTime", "streamSwitchInterval", "testid" })
	@Test
	public void receiverSwitchInputsFaioverNormalDistibutionTest(String remoteBX_login_ip, String remoteBXuserName, String remoteBXuserPassword, String remoteBXuiport,
     String testElapsedTime, String streamSwitchInterval, String testid) throws Exception {
		
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  remoteBX_login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version.
		this.version = productAboutDriver.getBroadcasterVersion(remoteBX_login_ip, remoteBXuiport, remoteBXuserName, remoteBXuserPassword);	
		buildTestParametersString(new String[] {"remoteBX_login_ip", "remoteBXuserName", "remoteBXuserPassword", "remoteBXuiport", "testElapsedTime", "streamSwitchInterval", "testid"}, 
		new String[] {remoteBX_login_ip,  remoteBXuserName, remoteBXuserPassword, remoteBXuiport, testElapsedTime, streamSwitchInterval, testid});
		
		driverReslut = ((BroadcasterSwitchInputsFaioverNormalDistributionDriver) testDriver).testNormalDistributioInputSwitchingReceiver(remoteBX_login_ip, remoteBXuserName, remoteBXuserPassword,
		remoteBXuiport, testElapsedTime, streamSwitchInterval);	
		Assert.assertEquals(driverReslut.getResult(), "passed");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  remoteBX_login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
	@Parameters({ "remoteBX_login_ip", "remoteBXuserName", "remoteBXuserPassword", "remoteBXuiport",
	"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "streamExcludeName", "testid" })
	@Test
	public void broadcasterSwitchInputsFaioverNormalDistibutionExcludeStreamTest(String remoteBX_login_ip, String remoteBXuserName, String remoteBXuserPassword, String remoteBXuiport,
    String login_ip, String userName, String userPassword, String uiport, String testElapsedTime, String streamSwitchInterval, String streamReferenceName, String streamExcludeName, String testid) throws Exception {
		
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"remoteBX_login_ip", "remoteBXuserName", "remoteBXuserPassword", "remoteBXuiport",
		"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "testid"}, 
		new String[] {remoteBX_login_ip, remoteBXuserName, remoteBXuserPassword, remoteBXuiport,
		login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval,streamReferenceName, testid});
		
		driverReslut = ((BroadcasterSwitchInputsFaioverNormalDistributionDriver) testDriver).testNormalDistributioInputSwitchingExclude(remoteBX_login_ip, remoteBXuserName, remoteBXuserPassword,
		remoteBXuiport, login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval, streamReferenceName, streamExcludeName);	
		Assert.assertEquals(driverReslut.getResult(), "passed");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
	@Parameters({ "remoteBX_login_ip", "remoteFXuserName", "remoteFXuserPassword", "remoteFXuiport",
		"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "streams", "testid" })
		@Test
		public void feederSwitchInputsFaioverNormalDistibution(String remoteBX_login_ip, String remoteFXuserName, 
		String remoteFXuserPassword, String remoteFXuiport, String login_ip, String userName, String userPassword, String uiport,
		String testElapsedTime, String streamSwitchInterval, String streamReferenceName, String streams, String testid) throws Exception {
			
			// Get broadcaster PID in the beginning of the test.
			sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
			// Retrieve the product version.
			this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
			buildTestParametersString(new String[] {"remoteBX_login_ip", "remoteFXuserName", "remoteFXuserPassword", "remoteFXuiport",
			"login_ip", "userName", "userPassword", "uiport", "testElapsedTime", "streamSwitchInterval", "streamReferenceName", "streams", "testid"}, 
			new String[] {remoteBX_login_ip, remoteFXuserName, remoteFXuserPassword, remoteFXuiport,
			login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval,streamReferenceName, streams, testid});
			
			driverReslut = ((BroadcasterSwitchInputsFaioverNormalDistributionDriver) testDriver).
			feederNormalDistributioInputSwitching(remoteBX_login_ip, remoteFXuserName, remoteFXuserPassword,
			remoteFXuiport, login_ip, userName, userPassword, uiport, testElapsedTime, streamSwitchInterval, streamReferenceName, streams);	
			
			Assert.assertEquals(driverReslut.getResult(), "passed");
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
		}
}