package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.drivers.ReceiverPullInputStreamCreationDriver;

public class ReceiverPullInputStreamCreationTest extends BaseTest{

	@BeforeClass
	public void testInit() {

		testDriver = new ReceiverPullInputStreamCreationDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "dec_key",
			"dec_type", "fec_adaptive", "fec_aware", "fec_force",
			"fec_latency", "fec_overhead", "host", "latency", "min_bit",
			"name", "nic", "port", "session", "stream" ,"testid" })
	@Test
	public void feederOutputToBxTest(String userName, String userPass,
			String login_ip, String uiport, String dec_key, String dec_type,
			String fec_adaptive, String fec_aware, String fec_force,
			String fec_latency, String fec_overhead, String host,
			String latency, String min_bit, String name, String nic,
			String port, String session, String stream,String testid)
			throws Exception {
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "dec_key",
				"dec_type", "fec_adaptive", "fec_aware", "fec_force",
				"fec_latency", "fec_overhead", "host", "latency", "min_bit",
				"name", "nic", "port", "session", "stream" ,"testid"}, 
				
				new String[] { userName, userPass, login_ip, uiport, dec_key,
				dec_type, fec_adaptive, fec_aware, fec_force,
				fec_latency, fec_overhead, host, latency, min_bit,
				name, nic, port, session, stream ,testid});
		
		
		Assert.assertEquals(
				((ReceiverPullInputStreamCreationDriver) testDriver).testIMPL(
						userName, userPass, login_ip, uiport, dec_key,
						dec_type, fec_adaptive, fec_aware, fec_force,
						fec_latency, fec_overhead, host, latency, min_bit,
						name, nic, port, session, stream),
				"Stream 'pull: " +host+ ":"+ port+ "/"+ stream + "' added.");
	}
}
