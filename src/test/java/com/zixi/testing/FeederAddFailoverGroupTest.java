package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FeederInputStreamDeletionDriver;

public class FeederAddFailoverGroupTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		testDriver = new FeederInputStreamDeletionDriver();
	}

	@Parameters({"userName","userPass", "login_ip", "uiport", "group_member_streams", "group_name", "search_window", "max_bitrate", "testid"})
	@Test
	public void broadcasterPullInCreation(String userName,String userPass, String login_ip, String uiport,
	String group_member_streams, String group_name, String search_window, String max_bitrate, String testid)
	throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] {"userName","userPass", "login_ip", "uiport", "group_member_streams", "group_name", "search_window", "max_bitrate", "testid"},
		new String[] {userName, userPass, login_ip, uiport, group_member_streams, group_name, search_window, max_bitrate, testid});
		
		driverReslut = ((FeederInputStreamDeletionDriver) testDriver)
		.addFailoverGroup(userName, userPass, login_ip, uiport, group_member_streams, group_name, search_window, max_bitrate);
		
		Assert.assertEquals(driverReslut.getResult(), "Failover group added");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}