package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSwitchOutputSourceDriver;


public class BroadcasterSwitchOutputSourceTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterSwitchOutputSourceDriver();
	}

	@Parameters({"userName","userPass", "login_ip", "uiport", "out_stream_id", "alternative_stream", "testid"})
	@Test
	public void broadcasterSwitchOutputSource(String userName, String userPass, String login_ip, String uiport, String out_stream_id,
    String alternative_stream, String testid)
	throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] {"userName","userPass", "login_ip", "uiport", "out_stream_id", "alternative_stream", "testid"}, 
		new String[] {userName, userPass, login_ip, uiport, out_stream_id, alternative_stream, testid});
		
		driverReslut = ((BroadcasterSwitchOutputSourceDriver) testDriver)
		.swithOutputSource(userName, userPass, login_ip, uiport, out_stream_id, alternative_stream);
		
		Assert.assertEquals(driverReslut.getResult(), "switched");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}