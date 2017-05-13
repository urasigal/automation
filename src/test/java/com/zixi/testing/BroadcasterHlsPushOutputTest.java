package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterHlsPushOutputDriver;

public class BroadcasterHlsPushOutputTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterHlsPushOutputDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "matrix", "stream", "type", "url", "cleanup", "encap", "no_tls", "testid"})
	@Test
	public void broadcasterPushHlsOutput(String userName, String userPass, String login_ip, String uiport, String name, String matrix, String stream,
	String type, String url, String cleanup, String encap, String no_tls, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name", "matrix", "stream", "type", "url", "cleanup", "encap", "no_tls", "testid"}, 
				
		new String[] {userName, userPass, login_ip, uiport, name, matrix, stream, type, url, cleanup, encap, no_tls, testid});
		
		driverReslut = ((BroadcasterHlsPushOutputDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, 
		name, matrix, stream, type, url, cleanup, encap, no_tls);
		
		Assert.assertEquals( driverReslut.getResult(), "Output " + name + " added.");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}