package com.zixi.transcoder.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterTransoderGetStatsDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterTransoderGetStatsTest extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterTransoderGetStatsDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "stream_name" ,"testid"})
	@Test
	public void broadcasterGetTranscoderStats(String userName, String userPass, String login_ip, String uiport, String stream_name, String testid) throws Exception
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "stream_name" ,"testid"}, 
		new String[] {userName, userPass, login_ip, uiport, stream_name, testid});
		
		driverReslut = ((BroadcasterTransoderGetStatsDriver) testDriver).getTranscoderStatistics(userName, userPass, login_ip, uiport, stream_name, testid);
		
		Assert.assertEquals( driverReslut.getResult(), "[0,0,0,0,0,0,0,0,0]");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
