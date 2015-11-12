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

public class FeederOutputDeletioinTest extends BaseTest{

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new FeederOutputDeletionDriver();
	}

	@Parameters({ "userName", "userPass", "loin_ip", "uiport", "id", "mip",
			"port", "ip", "prog", "chan", "type","host" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String loin_ip, String uiport, String id,
			String mip, String port, String ip, String prog, String chan,
			String type, String host,String testid) throws InterruptedException {
		this.testid = testid;
		Assert.assertEquals(((FeederOutputDeletionDriver) testDriver).testIMPL(
				userName, userPass, loin_ip, uiport, id, mip, port, ip, prog,
				chan, type, host), "Output deleted.");
	}
	
}
