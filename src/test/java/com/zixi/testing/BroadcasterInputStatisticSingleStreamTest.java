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

import com.zixi.drivers.BroadcasterInputStatisticSingleStreamDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;
import com.zixi.tools.TestlinkIntegration;

public class BroadcasterInputStatisticSingleStreamTest extends BaseTest {

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {

		// Load the page in the browser
		testDriver = new BroadcasterInputStatisticSingleStreamDriver();
	}

	@Parameters({ "userName", "userPass", "Host", "loin_ip", "uiport", "id",
			"testduration" ,"testid"})
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName,
			String userPass, String Host, String loin_ip, String uiport,
			String id, String testduration,String testid) throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(
				((BroadcasterInputStatisticSingleStreamDriver) testDriver)
						.testStatistic(userName, userPass, Host, loin_ip,
								uiport, id, testduration), "good");
	}

}
