package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushOutStreamCreationDriver;
import com.zixi.drivers.drivers.TestDriver;

public class BroadcasterPushOutStreamCreationTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPushOutStreamCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "host", "latency", "fec_force", "session", "fec_adaptive", "nic", "fec_block", "type",
	"snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias", "id" ,"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String login_ip, String host, String latency, String fec_force,
	String session, String fec_adaptive, String nic, String fec_block, String type, String snames, String fec_aware, String fec_overhead,
	String stream, String port, String uiport, String alias, String id, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "host", "latency",
		"fec_force", "session", "fec_adaptive", "nic", "fec_block", "type", "snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias", "id" ,"testid"}, 
		
		new String[] { userName, userPass, login_ip, host, latency, fec_force, session, fec_adaptive, nic, fec_block, type,
		snames, fec_aware, fec_overhead, stream, port, uiport, alias, id, testid });
		
		driverReslut = ((BroadcasterPushOutStreamCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, host, latency, fec_force,
		session, fec_adaptive, nic, fec_block, type, snames, fec_aware, fec_overhead, stream, port, uiport, alias, id);
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Output " + id + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster"));
	}
}
