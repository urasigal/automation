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

public class BroadcasterPushOutStreamCreationTest extends BaseTest {
	private TestDriver testDriver;

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
		
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "host", "latency",
		"fec_force", "session", "fec_adaptive", "nic", "fec_block", "type", "snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias", "id" ,"testid"}, 
		
		new String[] { userName, userPass, login_ip, host, latency, fec_force, session, fec_adaptive, nic, fec_block, type,
		snames, fec_aware, fec_overhead, stream, port, uiport, alias, id, testid });
		
		Assert.assertEquals(((BroadcasterPushOutStreamCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, host, latency, fec_force,
		session, fec_adaptive, nic, fec_block, type, snames, fec_aware, fec_overhead, stream, port, uiport, alias, id), "Output " + id + " added.");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster"));
	}
}
