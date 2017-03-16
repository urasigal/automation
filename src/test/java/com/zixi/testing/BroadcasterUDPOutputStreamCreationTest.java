package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterUdpOutputCreationDriver;

public class BroadcasterUDPOutputStreamCreationTest extends BaseTest{

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterUdpOutputCreationDriver(testFlowDescriptor); }

	@Parameters({ "userName", "userPass", "login_ip", "port", "stream", "streamname", "host", "id", "rtp", "fec", "smoothing", "ttl",
	"remux_bitrate", "df", "local_port", "dec_key", "type", "rows", "remux_buff", "local_ip", "remux_restampdts", "uiport", "remux_pcr", "dec_type", "cols" ,"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String login_ip, String port, String stream, String streamname,
	String host, String id, String rtp, String fec, String smoothing, String ttl, String remux_bitrate, String df, String local_port,
	String dec_key, String type, String rows, String remux_buff, String local_ip, String remux_restampdts, String uiport, String remux_pcr,
	String dec_type, String cols,String testid) throws Exception {
		
		testFlowDescriptor.append("\nStarting the BroadcasterUDPOutputStreamCreationTest test");
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testLinktestParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "port", "stream",
		"streamname", "host", "id", "rtp", "fec", "smoothing", "ttl", "remux_bitrate", "df", "local_port", "dec_key", "type", "rows",
		"remux_buff", "local_ip", "remux_restampdts", "uiport", "remux_pcr", "dec_type", "cols" ,"testid"}, 
		new String[] {userName, userPass, login_ip, port, stream, streamname, host, id, rtp, fec, smoothing, ttl, remux_bitrate, df, local_port, dec_key, type, rows,
		remux_buff, local_ip, remux_restampdts, uiport, remux_pcr, dec_type, cols ,testid });
		
		driverReslut = ((BroadcasterUdpOutputCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, port, stream, streamname, host, id, rtp, fec, smoothing,
		ttl, remux_bitrate, df, local_port, dec_key, type, rows, remux_buff, local_ip, remux_restampdts, uiport, remux_pcr, dec_type, cols);
		
		Assert.assertEquals(driverReslut.getResult(), "Output " + id + " added.");
		
		// Checking if broadcaster has crashed while test execution.
		String postTestPid = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		Assert.assertEquals(sutProcessId, postTestPid);
	}
}
