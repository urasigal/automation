package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederAddHTTPPullInputDriver;

public class FeederAddHTTPPullInputTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederAddHTTPPullInputDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "http_url", "latency", "stream_name", "testid" })
	@Test
	public void feederAddHTTPPull(String userName, String userPass, String login_ip, String uiport, String http_url,
			String latency, String stream_name, String testid) throws Exception {
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "http_url", "latency", "stream_name", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, http_url, latency, stream_name, testid });
		
		driverReslut = ((FeederAddHTTPPullInputDriver) testDriver).addHttpIn( userName, userPass, login_ip, uiport, http_url, latency, stream_name, testid);
		
		Assert.assertEquals(driverReslut.getResult(), "TS TCP Input added");
	}
}
