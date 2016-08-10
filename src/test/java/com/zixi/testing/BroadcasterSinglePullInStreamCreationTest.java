package com.zixi.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterSinglePullInStreamCreationTest extends BaseTest {

	
	//The method will be run before the first test method in the current class is invoked.
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterSinglePullInStreamCreationDriver();
	}

	
	@Parameters({ "userName", "userPass", "Host", "login_ip", "id", "source",
			"uiport", "pull_port", "latency", "fec_latency", "fec_overhead",
			"mcast_force", "time_shift", "nic", "max_outputs", "type",
			"password", "mcast_port", "complete", "mcast_ip", "fec_adaptive",
			"mcast_ttl", "on", "func", "fec_force", "mcast_out",
			"propertiesFile" ,"testid"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass,
			String Host, String login_ip, String id, String source,
			String uiport, String pull_port, String latency,
			String fec_latency, String fec_overhead, String mcast_force,
			String time_shift, String nic, String max_outputs, String type,
			String password, String mcast_port, String complete,
			String mcast_ip, String fec_adaptive, String mcast_ttl, String on,
			String func, String fec_force, String mcast_out,
			String propertiesFile,String testid) throws Exception {
		
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Gather the test parameters in order to pass them to the TestLink
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "Host", "login_ip", "id", "source",
				"uiport", "pull_port", "latency", "fec_latency", "fec_overhead",
				"mcast_force", "time_shift", "nic", "max_outputs", "type",
				"password", "mcast_port", "complete", "mcast_ip", "fec_adaptive",
				"mcast_ttl", "on", "func", "fec_force", "mcast_out",
				"propertiesFile" ,"testid"}, 
				
				new String[] {userName, userPass, Host, login_ip, id, source,
				uiport, pull_port, latency, fec_latency, fec_overhead,
				mcast_force, time_shift, nic, max_outputs, type,
				password, mcast_port, complete, mcast_ip, fec_adaptive,
				mcast_ttl, on, func, fec_force, mcast_out,
				propertiesFile ,testid });
		
		// The actual test method.
		Assert.assertEquals(
				((BroadcasterSinglePullInStreamCreationDriver) testDriver)
						.testIMPL(userName, userPass, Host, login_ip, id,
								source, uiport, pull_port, latency,
								fec_latency, fec_overhead, mcast_force,
								time_shift, nic, max_outputs, type, password,
								mcast_port, complete, mcast_ip, fec_adaptive,
								mcast_ttl, on, func, fec_force, mcast_out,
								propertiesFile), "Stream " + "'" + id + "'"
						+ " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
