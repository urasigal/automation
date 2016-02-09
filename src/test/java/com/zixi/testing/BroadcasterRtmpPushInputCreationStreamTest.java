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

public class BroadcasterRtmpPushInputCreationStreamTest extends BaseTest {
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterRtmpPushInputStreamDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix",
			"max_outputs", "mcast_out", "time_shift", "old", "fast_connect",
			"kompression", "enc_type", "enc_key", "rec_history",
			"rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "testid" })
	@Test
	public void broadcasterRtmpPushTest(String userName, String userPass,
			String login_ip, String uiport,String type, String id, String matrix,
			String max_outputs, String mcast_out, String time_shift,
			String old, String fast_connect, String kompression,
			String enc_type, String enc_key, String rec_history,
			String rec_duration, String rtmp_url, String rtmp_name,
			String rtmp_user, String testid) throws InterruptedException {
		this.testid = testid;
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix",
					"max_outputs", "mcast_out", "time_shift", "old", "fast_connect",
					"kompression", "enc_type", "enc_key", "rec_history",
					"rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "testid" }, 
				new String[] { userName, userPass, login_ip, uiport, type, id, matrix,
								max_outputs, mcast_out, time_shift, old, fast_connect,
								kompression, enc_type, enc_key, rec_history,
								rec_duration, rtmp_url, rtmp_name, rtmp_user, testid });
		
		
		Assert.assertEquals(((BroadcasterRtmpPushInputStreamDriver) testDriver)
				.testIMPL(userName, userPass, login_ip, uiport, type, id, matrix,
						max_outputs, mcast_out, time_shift, old, fast_connect,
						kompression, enc_type, enc_key, rec_history,
						rec_duration, rtmp_url, rtmp_name, rtmp_user),
				"Stream " + "'" + id + "'" + " added.");
	}

}
