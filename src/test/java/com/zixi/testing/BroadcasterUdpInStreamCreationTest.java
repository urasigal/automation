package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSingleInputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterSingleUdpInCreationDriver;
import com.zixi.drivers.drivers.TestDriver;
import com.zixi.tools.Prerequisitor;

public class BroadcasterUdpInStreamCreationTest extends BaseTest {

	private Prerequisitor prerequisitor;
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterSingleUdpInCreationDriver();
	}

	
	@Parameters({ "userName", "userPass", "login_ip", "ts_port", "id", "rtp_type", "multi_src", "max_bitrate", "time_shift", "mcast_ip",
	"mcast_force", "mcast_port", "nic", "type", "multicast", "enc_key", "kompression", "uiport", "mcast_ttl", "enc_type", "mcast_out",
	"complete", "max_outputs", "on" ,"testid"})
	
	@Test
	public void broadcasterUdpInCreation(String userName, String userPass, String login_ip, String ts_port, String id, String rtp_type,
	String multi_src, String max_bitrate, String time_shift, String mcast_ip, String mcast_force, String mcast_port, String nic,
	String type, String multicast, String enc_key, String kompression, String uiport, String mcast_ttl, String enc_type, String mcast_out,
	String complete, String max_outputs, String on,String testid) throws Exception { 
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		String[] params = new String[] { userName, userPass, login_ip, ts_port, id,
		rtp_type, multi_src, max_bitrate, time_shift, mcast_ip,
		mcast_force, mcast_port, nic, type, multicast, 
		enc_key, kompression, uiport, mcast_ttl, enc_type, 
		mcast_out, complete, max_outputs, on ,testid };
		
		testParameters = testBaseFunction.buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "ts_port", "id",
		"rtp_type", "multi_src", "max_bitrate", "time_shift", "mcast_ip",
		"mcast_force", "mcast_port", "nic", "type", "multicast", "enc_key",
		"kompression", "uiport", "mcast_ttl", "enc_type", "mcast_out",
		"complete", "max_outputs", "on" ,"testid" }, params);
		
		
		 prerequisitor  = (param) -> {BroadcasterSingleInputStreamDeletionDriver testDriver = new BroadcasterSingleInputStreamDeletionDriver();
		 							 try {
										testDriver.removeInput( param[2], param[0], param[1], param[4], param[17]);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}};
		 prerequisitor.setToExecutionLevel(params);
		
		 Assert.assertEquals(((BroadcasterSingleUdpInCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, ts_port, id, rtp_type,
		 multi_src, max_bitrate, time_shift, mcast_ip, mcast_force, mcast_port, nic, type, multicast, enc_key,
		 kompression, uiport, mcast_ttl, enc_type, mcast_out, complete, max_outputs, on), "Stream " + "'" + id + "'" + " added.");
		 // Checking if broadcaster has crashes while execution of the test.
		 Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
