package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterHlsPullInputDriver;
import com.zixi.drivers.drivers.BroadcasterInputsStatsDriver;

public class BroadcasterInputsStatsTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterInputsStatsDriver(); }

	@Parameters({"userName", "userPass", "login_ip", "uiport", "wait_time", "up_down", "testid"})
	@Test
	public void broadcasterInputsUpDownTotalTimeTest(String userName, String userPass, String login_ip, String uiport, String wait_time,
	String up_down, String testid) throws Exception {
		 
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "wait_time", "up_down", "testid"}, 
				
		new String[] {userName, userPass, login_ip, uiport, wait_time, up_down, testid});
		
		driverReslut = ((BroadcasterInputsStatsDriver)testDriver).totalUpDownTime(userName, userPass, login_ip, uiport, wait_time, up_down);
		
		Assert.assertEquals( driverReslut.getResult(), "Excpected behavior");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}