package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.ReceiverAddUdpInputDriver;

public class ReceiverAddUdpInputTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {testDriver = new ReceiverAddUdpInputDriver();}
	@Parameters({ "login_ip", "userName", "userPass", "uiport" ,"stream_name","host", "port", "nic", "testid"})
	@Test		
	public void receiverDeleteOutputStreamTest(String login_ip, String userName, String userPass, String uiport ,String stream_name,
	String host, String port, String nic, String testid) 
	throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		buildTestParametersString(new String[] { "login_ip", "userName", "userPass", "uiport" ,"stream_name","host", "port", "nic", "testid"}, 	
		new String[] { login_ip, userName, userPass, uiport, stream_name, host, port, nic, testid});
		
		driverReslut = ((ReceiverAddUdpInputDriver) testDriver).addUdpInput(login_ip, userName, userPass, uiport, stream_name, host, port, nic);		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream 'udp: "+ port + "' added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
}
