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

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterInputStatisticSingleStreamDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterInputStatisticSingleStreamTest extends BaseTest {

	@BeforeClass
	public void testInit() {
		newTestDriver = new com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver();
	}

	@Parameters({ "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String userName, String userPass, String Host, String login_ip, String uiport,
	String id, String testduration,String testid) throws Exception {
		
		this.testid = testid;
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve a product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "Host", "login_ip", "uiport", "id", "testduration" ,"testid"}, 
		new String[] { userName, userPass, Host, login_ip, uiport, id, testduration ,testid });
		
		// Perform the statistic test.
		Assert.assertEquals(((com.zixi.drivers.drivers.BroadcasterInputStatisticSingleStreamDriver) newTestDriver).testStatistic(userName, userPass, Host, login_ip,
		uiport, id, testduration), "statistics parameters are correct");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
