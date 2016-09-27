package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterSingleInputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterSingleInputStreamDeletionTest extends BaseTest {

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		// Load the page in the browser
		testDriver = new BroadcasterSingleInputStreamDeletionDriver();
	}

	@Parameters({ "login_ip", "userName", "userPassword", "streamId", "uiport" ,"testid"})
	@Test
	public void broadcasterInputStreamDeletion(String login_ip, String userName, String userPassword, String streamId, String uiport, String testid) throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster" );
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		
		testParameters = buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "streamId", "uiport" ,"testid" }, 
		new String[] {login_ip, userName, userPassword, streamId, uiport ,testid });
		
		Assert.assertNotNull(((BroadcasterSingleInputStreamDeletionDriver) testDriver).removeInput(login_ip, userName, userPassword, streamId, uiport));
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}