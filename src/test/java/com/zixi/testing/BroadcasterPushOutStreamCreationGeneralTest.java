package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushOutStreamCreationDriver;
import com.zixi.drivers.drivers.TestDriver;

public class BroadcasterPushOutStreamCreationGeneralTest extends BaseTest {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterPushOutStreamCreationDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "host", "latency", "fec_force", "session", "fec_adaptive", "nic", "fec_block", "type",
	"snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias", "id", "link1", "link2", "bond_links", "testid" })
	@Test
	public void broadcasterPushOutCreationWithTwoRedundantHosts(String userName, String userPass, String login_ip, String host, String latency, String fec_force,
	String session, String fec_adaptive, String nic, String fec_block, String type, String snames, String fec_aware, String fec_overhead,
	String stream, String port, String uiport, String alias, String id, String link1, String link2, String bond_links, String testid) throws Exception {
		
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "host", "latency", "fec_force", "session", "fec_adaptive", "nic", "fec_block", "type",
		"snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias", "id", "link1", "link2", "bond_links", "testid"}, 
		
		new String[] { userName, userPass, login_ip, host, latency, fec_force, session, fec_adaptive, nic, fec_block, type, snames, fec_aware, fec_overhead, 
		stream, port, uiport, alias, id, link1, link2, bond_links, testid });
		
		driverReslut = ((BroadcasterPushOutStreamCreationDriver) testDriver).testIMPLRedund(userName, userPass, login_ip, host, latency, fec_force,
		session, fec_adaptive, nic, fec_block, type, snames, fec_aware, fec_overhead, stream, port, uiport, alias, id, link1, link2, bond_links);
		
		
		Assert.assertEquals(driverReslut.getResult(), "Output " + id + " added.");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster"));
	}
}
