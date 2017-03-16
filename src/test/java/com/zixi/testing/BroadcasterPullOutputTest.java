package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPullOutputDriver;

public class BroadcasterPullOutputTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPullOutputDriver();
	}

	@Parameters({ 
		"userName","userPass", "login_ip", "uiport","type","name","stream","matrix","alt_stream","remote_id","session","latency","session_auth","stats_hist","testid"})
	@Test
	public void broadcasterPullInCreation(String userName,String userPass, String login_ip, String uiport, String type, String name, String stream, String matrix, String alt_stream,
	String remote_id, String session, String latency, String session_auth, String stats_hist ,String testid)
	throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testLinktestParameters = buildTestParametersString(new String[] {"userName", "userPass",  "login_ip", "uiport", "type", "name", "stream", "matrix",
		"alt_stream", "remote_id", "session", "latency", "session_auth", "stats_hist" ,"testid"}, new String[] { userName, userPass, login_ip,
		uiport, type, name, stream, matrix, alt_stream, remote_id, session, latency, session_auth, stats_hist, testid });
		
		driverReslut = ((BroadcasterPullOutputDriver) testDriver)
		.testIMPL(userName, userPass,  login_ip,  uiport, type, name, stream, matrix,alt_stream, remote_id, session, latency, session_auth,stats_hist);
		
		Assert.assertNotNull(driverReslut.getResult());
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}