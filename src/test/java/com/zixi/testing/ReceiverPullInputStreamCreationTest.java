package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.ReceiverPullInputStreamCreationDriver;

public class ReceiverPullInputStreamCreationTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new ReceiverPullInputStreamCreationDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "dec_key", "dec_type", "fec_adaptive", "fec_aware", "fec_force","fec_latency", "fec_overhead", 
	"host", "latency", "min_bit", "name", "nic", "port", "session", "stream" ,"testid" })
	@Test
	public void feederOutputToBxTest(String userName, String userPass, String login_ip, String uiport, String dec_key, String dec_type,
	String fec_adaptive, String fec_aware, String fec_force, String fec_latency, String fec_overhead, String host, String latency, String min_bit, String name, String nic,
	String port, String session, String stream,String testid) throws Exception {
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver");
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "dec_key", "dec_type", "fec_adaptive", "fec_aware", "fec_force",
		"fec_latency", "fec_overhead", "host", "latency", "min_bit", "name", "nic", "port", "session", "stream" ,"testid"}, 
				
		new String[] { userName, userPass, login_ip, uiport, dec_key, dec_type, fec_adaptive, fec_aware, fec_force, fec_latency, fec_overhead, host, latency, min_bit,
		name, nic, port, session, stream ,testid});
		
		driverReslut = ((ReceiverPullInputStreamCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, dec_key, dec_type, fec_adaptive, fec_aware,
		fec_force, fec_latency, fec_overhead, host, latency, min_bit, name, nic, port, session, stream);
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_receiver` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertTrue((driverReslut.getResult().equals("Stream 'pull: " +host+ ":"+ port+ "/"+ stream + "' added.")) || ( driverReslut.getResult().equals("Stream 'pull: " +host+ ":"+ port+ "/"+ stream + " NIC: ' added.")));
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_receiver"));
	}
}
