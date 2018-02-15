package com.zixi.testasutility;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.RestartDriver;
import com.zixi.testing.BasetTestZixiComponentRestartCase;

public class RestartTest extends BasetTestZixiComponentRestartCase{
	
	@BeforeClass
	public void testInit() {testDriver = new RestartDriver(); }

	@Parameters({"userName","userPass","login_ip", "uiport", "testid"})
	@Test
	public void restartSUT(String userName, String userPass, String login_ip, String uiport, String testid)
	throws Exception {
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId 	= BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_feeder");
		buildTestParametersString(new String[] { "userName","userPass","login_ip", "uiport", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, testid });
		((RestartDriver) testDriver).restart(userName, userPass, login_ip, uiport);
		Assert.assertNotEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_feeder"));
	}
}