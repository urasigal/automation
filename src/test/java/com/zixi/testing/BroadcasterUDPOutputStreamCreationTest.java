package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterUDPOutputStreamCreationTest extends BaseTest{
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterUdpOutputCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "port", "stream",
			"streamname", "host", "id", "rtp", "fec", "smoothing", "ttl",
			"remux_bitrate", "df", "local_port", "dec_key", "type", "rows",
			"remux_buff", "local_ip", "remux_restampdts", "uiport",
			"remux_pcr", "dec_type", "cols" ,"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass,
			String login_ip, String port, String stream, String streamname,
			String host, String id, String rtp, String fec, String smoothing,
			String ttl, String remux_bitrate, String df, String local_port,
			String dec_key, String type, String rows, String remux_buff,
			String local_ip, String remux_restampdts, String uiport,
			String remux_pcr, String dec_type, String cols,String testid)
			throws InterruptedException {
		this.testid = testid;
		
		pid = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "port", "stream",
				"streamname", "host", "id", "rtp", "fec", "smoothing", "ttl",
				"remux_bitrate", "df", "local_port", "dec_key", "type", "rows",
				"remux_buff", "local_ip", "remux_restampdts", "uiport",
				"remux_pcr", "dec_type", "cols" ,"testid"}, 
				
				new String[] {userName, userPass, login_ip, port, stream,
				streamname, host, id, rtp, fec, smoothing, ttl,
				remux_bitrate, df, local_port, dec_key, type, rows,
				remux_buff, local_ip, remux_restampdts, uiport,
				remux_pcr, dec_type, cols ,testid });
		
		
		Assert.assertNotNull(((BroadcasterUdpOutputCreationDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, port, stream,
						streamname, host, id, rtp, fec, smoothing, ttl,
						remux_bitrate, df, local_port, dec_key, type, rows,
						remux_buff, local_ip, remux_restampdts, uiport,
						remux_pcr, dec_type, cols));
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(pid, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
}
