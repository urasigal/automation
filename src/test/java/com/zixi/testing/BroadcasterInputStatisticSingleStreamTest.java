package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver;

public class BroadcasterInputStatisticSingleStreamTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterInputStatisticSingleStreamDriver(); }

	@Parameters({ "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String userName, String userPass, String Host, String login_ip, String uiport,
	String id, String testduration, String testid) throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve a product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"}, 
		new String[] { userName, userPass, Host, login_ip, uiport, id, testduration ,testid });
		
		driverReslut = ((com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver) testDriver).testStatistic(userName, userPass, Host, login_ip, uiport, id, testduration);
		
		// Perform the statistic test.
		Assert.assertEquals(driverReslut.getResult(), "statistic was accepted");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
