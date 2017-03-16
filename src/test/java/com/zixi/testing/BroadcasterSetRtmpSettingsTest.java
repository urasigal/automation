package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSetRtmpSettingsDriver;
import com.zixi.drivers.drivers.TestDriver;

public class BroadcasterSetRtmpSettingsTest extends BaseTest {
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSetRtmpSettingsDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "rtmp_on", "rtmp_port", "rtmp_auto_out", "rtmp_auto_in", "rtmp_pcr_int", "rtmp_auto_out_latency", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String rtmp_on, String rtmp_port,
	String rtmp_auto_out, String rtmp_auto_in, String rtmp_pcr_int, String rtmp_auto_out_latency, String testid) throws Exception {
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		testLinktestParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "rtmp_on", "rtmp_port",
		"rtmp_auto_out", "rtmp_auto_in", "rtmp_pcr_int", "rtmp_auto_out_latency", "testid" }, 
		new String[] { userName, userPass, login_ip, uiport, rtmp_on, rtmp_port, rtmp_auto_out, rtmp_auto_in, rtmp_pcr_int, rtmp_auto_out_latency, testid });
		
		driverReslut = ((BroadcasterSetRtmpSettingsDriver) testDriver).testIMPL(userName, userPass,  login_ip, uiport, rtmp_on,  rtmp_port,
		rtmp_auto_out,  rtmp_auto_in,  rtmp_pcr_int, rtmp_auto_out_latency);
		
		Assert.assertEquals(driverReslut.getResult(), "1");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
