package com.zixi.load.testing;

import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultipleCreationDriver;
import com.zixi.testing.BaseTest;


public class BroadcasterMultipleRtmpPullCreationTest extends BaseTest
{
	//The method will be run before the first test's method invocation in the current class.
	@BeforeClass
	public void testInit()
	{
		testDriver = new BroadcasterMultipleCreationDriver(testFlowDescriptor);
	}
	
	// Parameters section.
	@Parameters({"userName", "userPass", "login_ip", "rtmp_nulls", "id", "rtmp_url", "rtmp_name", "time_shift", "mcast_ip", "mcast_force", "mcast_port", "type",
	"rtmp_user", "rtmp_bitrate", "rtmp_passwd", "uiport", "mcast_ttl", "rtmp_latency", "mcast_out", "complete", "max_outputs", "on", 
	"number_of_streams", "testid"})
	
	// Test method section. 
	@Test
	public void broadcasterMultipleRtmpPullInCreation(String userName, String userPass, String login_ip, String rtmp_nulls, String id, String rtmp_url,
			String rtmp_name, String time_shift, String mcast_ip, String mcast_force, String mcast_port, String type,
			String rtmp_user, String rtmp_bitrate, String rtmp_passwd, String uiport, String mcast_ttl, String rtmp_latency, String mcast_out, 
			String complete, String max_outputs, String on, String number_of_streams, String testid) throws Exception
	{
		// Get a SUT build number - SSH access is required. 
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		// Get a SUT PID number in the beginning of the test. 
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Gather the test parameters in order to pass them to the TestLink
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "rtmp_nulls","id", "rtmp_url", "rtmp_name",
		"time_shift", "mcast_ip", "mcast_force", "mcast_port", "type", "rtmp_user", "rtmp_bitrate", "rtmp_passwd", "uiport", "mcast_ttl", "rtmp_latency", 
		"mcast_out", "complete", "max_outputs", "on", "number_of_streams", "testid"}, 
		new String[] {userName, userPass, login_ip, rtmp_nulls, id, rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force, mcast_port, type,
		rtmp_user, rtmp_bitrate, rtmp_passwd, uiport, mcast_ttl, rtmp_latency, mcast_out, complete, max_outputs, on, 
		number_of_streams, testid});
		
		// The actual test method.
		// The condition of a passed test is if the number of added streams is equal to the number of the desired steams.
		Assert.assertEquals(((BroadcasterMultipleCreationDriver) testDriver).testIMPL
								(userName, userPass, login_ip, rtmp_nulls, id, rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force, mcast_port, type,
								rtmp_user, rtmp_bitrate, rtmp_passwd, uiport, mcast_ttl, rtmp_latency, mcast_out, complete, max_outputs, on, number_of_streams), "pass");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	} 
}
