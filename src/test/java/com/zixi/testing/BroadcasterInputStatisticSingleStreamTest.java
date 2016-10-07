package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;

public class BroadcasterInputStatisticSingleStreamTest extends BaseTest {

	@BeforeClass
	public void testInit() {
		testDriver = new com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver();
	}

	@Parameters({ "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String userName, String userPass, String Host, String login_ip, String uiport,
	String id, String testduration,String testid) throws Exception {
		
		this.testid = testid;
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve a product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"}, 
		new String[] { userName, userPass, Host, login_ip, uiport, id, testduration ,testid });
		
		// Perform the statistic test.
		Assert.assertEquals(((com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver) testDriver).testStatistic(userName, userPass, Host, login_ip,
		uiport, id, testduration), "statistics parameters are correct");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
