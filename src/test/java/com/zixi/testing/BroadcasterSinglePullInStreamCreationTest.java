package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSinglePullInStreamCreationDriver;

public class BroadcasterSinglePullInStreamCreationTest extends BaseTestZixiMainComponents {

	
	//The method will be run before the first test method in the current class is invoked.
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSinglePullInStreamCreationDriver(); }

	@Parameters({ "userName", "userPass", "Host", "login_ip", "id", "source",
	"uiport", "pull_port", "latency", "fec_latency", "fec_overhead", "mcast_force", "time_shift", "nic", "max_outputs", "type",
	"password", "mcast_port", "complete", "mcast_ip", "fec_adaptive", "mcast_ttl", "on", "func", "fec_force", 
	"mcast_out", "propertiesFile", "mtu", "rec_duration", "rec_template", "s3", "s3_url", "s3_no_tls", "testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass,
	String Host, String login_ip, String id, String source, String uiport, String pull_port, String latency, String fec_latency, String fec_overhead, String mcast_force,
	String time_shift, String nic, String max_outputs, String type, String password, String mcast_port, String complete,
	String mcast_ip, String fec_adaptive, String mcast_ttl, String on, String func, String fec_force, String mcast_out, String propertiesFile ,
	@Optional("0") String mtu,  @Optional("7200") String rec_duration, @Optional("%S_%Y%M%D-%T.ts") String rec_template, 
	@Optional("0") String s3, @Optional("") String s3_url, @Optional("0") String s3_no_tls, String testid) throws Exception { 
		
		String buildNumber = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		// Gather the test parameters in order to pass them to the TestLink
		buildTestParametersString(new String[] { "userName", "userPass", "Host", "login_ip", "id", "source", "uiport", "pull_port", "latency", "fec_latency", "fec_overhead",
		"mcast_force", "time_shift", "nic", "max_outputs", "type", "password", "mcast_port", "complete", "mcast_ip", "fec_adaptive",
		"mcast_ttl", "on", "func", "fec_force", "mcast_out", "propertiesFile", "rec_duration", "rec_template", "s3", "s3_url", "s3_no_tls", "testid"}, 
		new String[] {userName, userPass, Host, login_ip, id, source, uiport, pull_port, latency, fec_latency, 
		fec_overhead, mcast_force, time_shift, nic, max_outputs, type,
		password, mcast_port, complete, mcast_ip, fec_adaptive, mcast_ttl, on, func, fec_force, 
		mcast_out, propertiesFile, rec_duration, rec_template, s3, s3_url, s3_no_tls, testid });
		
		driverReslut = ((BroadcasterSinglePullInStreamCreationDriver) testDriver).addPull(userName, userPass, Host, login_ip, id, source, uiport, pull_port, latency, fec_latency,
		fec_overhead, mcast_force, time_shift, nic, max_outputs, type, password, mcast_port, complete, mcast_ip, fec_adaptive, mcast_ttl, on, func, fec_force, mcast_out,
		propertiesFile, mtu, rec_duration, rec_template, s3, s3_url, s3_no_tls);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		// The actual test method.
		Assert.assertEquals( driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
