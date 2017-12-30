package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.ReceiverUdpOutCreationDriver;

public class ReceiverUdpOutCreationTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() {testDriver = new ReceiverUdpOutCreationDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "target", "type", "nic", "ttl", "smoothing", "rtp", "fec", "rows",
	"cols", "remux_bitrate", "input_stream", "testid" })
	@Test
	public void receiverOutputUdpTest(String userName, String userPass,String login_ip, String uiport, String name, String target,
	String type, String nic, String ttl, String smoothing, String rtp,String fec, String rows, String cols, String remux_bitrate, String input_stream,
	String testid) throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "name",
		"target", "type", "nic", "ttl", "smoothing", "rtp", "fec", "rows","cols", "remux_bitrate", "input_stream", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, name,target, type, nic, ttl, smoothing, rtp, fec, rows,cols, remux_bitrate, input_stream, testid });
		
		driverReslut = ((ReceiverUdpOutCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, name, target, type, nic, ttl, smoothing, rtp, fec, rows,
		cols, remux_bitrate , input_stream);		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream "  + "'"+ target + "'"+ " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
}
