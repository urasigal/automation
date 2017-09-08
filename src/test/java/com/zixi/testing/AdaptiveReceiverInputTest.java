package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.maxwell.drivers.AdaptiveReceiverInputDriver;

public class AdaptiveReceiverInputTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new AdaptiveReceiverInputDriver(); } // Super class element

	// Test parameters.
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "stream_id", "maxwellIp", "maxwellPort", "maxwell_match_control", "maxwellImpairmentsCommand", "testid" })
	@Test
	public void receiverAdaptiveInputDownCaseTest(String login_ip, String userName, String userPassword, String uiport, String stream_id, 
	String maxwellIp, String maxwellPort, String maxwell_match_control, String maxwellImpairmentsCommand, String testid) throws Exception 
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_receiver");
		
		buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "uiport", "stream_id", "maxwellIp", 
		"maxwellPort", "maxwell_match_control", "maxwellImpairmentsCommand", "testid" }, 
		new String[] { login_ip, userName, userPassword, uiport, stream_id, maxwellIp, maxwellPort, maxwell_match_control, maxwellImpairmentsCommand, testid });
		
		driverReslut = ((AdaptiveReceiverInputDriver) testDriver).testIMPL( login_ip, userName, userPassword, uiport, stream_id,
		maxwellIp, maxwell_match_control, maxwellPort, maxwellImpairmentsCommand);
		
		Assert.assertEquals(driverReslut.getResult(), "Test passed accordingly predefined parameters");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
	
	
		// Test parameters.
		@Parameters({ "login_ip", "userName", "userPassword", "uiport", "stream_id", "maxwellIp", "maxwellPort", "maxwell_match_control", "maxwellImpairmentsCommand", "testid" })
		@Test
		public void receiverAdaptiveInputUpCaseTest(String login_ip, String userName, String userPassword, String uiport, String stream_id, 
		String maxwellIp, String maxwellPort, String maxwell_match_control, String maxwellImpairmentsCommand, String testid) throws Exception 
		{
			sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_receiver");
			
			buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "uiport", "stream_id", "maxwellIp", 
			"maxwellPort", "maxwell_match_control", "maxwellImpairmentsCommand", "testid" }, 
			new String[] { login_ip, userName, userPassword, uiport, stream_id, maxwellIp, maxwellPort, maxwell_match_control, maxwellImpairmentsCommand, testid });
			
			driverReslut = ((AdaptiveReceiverInputDriver) testDriver).testIMPLUpCase( login_ip, userName, userPassword, uiport, stream_id,
			maxwellIp, maxwell_match_control, maxwellPort, maxwellImpairmentsCommand);
			
			Assert.assertEquals(driverReslut.getResult(), "Test passed accordingly predefined parameters");
			// Checking if broadcaster has crashes while execution of the test.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
		}
}