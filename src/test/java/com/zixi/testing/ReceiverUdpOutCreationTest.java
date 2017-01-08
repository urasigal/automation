package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverUdpOutCreationDriver;

public class ReceiverUdpOutCreationTest extends BaseTest {

	@BeforeClass
	public void testInit() {testDriver = new ReceiverUdpOutCreationDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "target", "type", "nic", "ttl", "smoothing", "rtp", "fec", "rows",
	"cols", "remux_bitrate", "input_stream", "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass,String login_ip, String uiport, String name, String target,
	String type, String nic, String ttl, String smoothing, String rtp,String fec, String rows, String cols, String remux_bitrate, String input_stream,
	String testid) throws Exception {
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name",
		"target", "type", "nic", "ttl", "smoothing", "rtp", "fec", "rows","cols", "remux_bitrate", "input_stream", "testid"}, 
		
		new String[] { userName, userPass, login_ip, uiport, name,target, type, nic, ttl, smoothing, rtp, fec, rows,cols, remux_bitrate, input_stream, testid });
		
		driverReslut = ((ReceiverUdpOutCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, name, target, type, nic, ttl, smoothing, rtp, fec, rows,
		cols, remux_bitrate , input_stream);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream "  + "'"+ target + "'"+ " added.");
	}

}
