package com.zixi.load.testing;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultipleCreationDriver;
import com.zixi.load.drivers.BroadcasterMultiplePushDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterMultiplePushManualTest extends BaseTestZixiMainComponents{
	//The method will be run before the first test's method invocation in the current class.
	@Parameters({"testid"})
	@BeforeClass
	public void testInit(String testid) {
		this.testid = testid;
		testDriver = new BroadcasterMultiplePushDriver();
	}
	
	@Parameters({ "userName_bx1", "userName_bx2", "userPass_bx1", "userPass_bx2", "login_ip_bx1", "login_ip_bx2", "latency_bx1", "time_shift",
	"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type_bx1", "uiport_bx1", "analyze", "mcast_ttl", "id_bx1", "mcast_out",
	"complete", "max_outputs", "on", "password", "host", "latency_bx2", "fec_force", "session", "fec_adaptive",
	"nic", "fec_block", "type_bx2", "snames", "fec_aware", "fec_overhead", "stream", "port", "uiport_bx2", "alias", "id_bx2" ,
	"number_of_streams", "mixed", "testid"})
	@Test
	public void broadcasterMultiplePullInCreation(String userName_bx1, String userName_bx2, String userPass_bx1, String userPass_bx2, String login_ip_bx1, 
	String login_ip_bx2, String latency_bx1, String time_shift ,String force_p2p, String mcast_ip, String mcast_force, String mcast_port, 
	String type_bx1, String uiport_bx1, String analyze, String mcast_ttl, String id_bx1, String mcast_out,
	String complete, String max_outputs, String on, String password, String host, String latency_bx2, String fec_force, String session, 
	String fec_adaptive, String nic, String fec_block, String type_bx2, String snames, String fec_aware, String fec_overhead, String stream, 
	String port, String uiport_bx2, String alias, String id_bx2, String number_of_streams, @Optional("0") String mixed, String testid) throws Exception {
		productAboutDriver.getBroadcasterVersion(login_ip_bx1, uiport_bx1, userName_bx1, userPass_bx1);
		// PIDs from a two broadcaster servers because of in this particular test case a two different broadcasters are involved.
		sutProcessId         = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "pidof zixi_broadcaster");
		String sutProcessId2 = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx2,  "22",  "pidof zixi_broadcaster");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		String tmpStartMemHolder = memOnStart;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx2,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		// Gather the test parameters in order to pass them to the TestLink
		buildTestParametersString(new String[] { "userName_bx1", "userName_bx2", "userPass_bx1", "userPass_bx2", "login_ip_bx1", "login_ip_bx2", "latency_bx1", "time_shift",
		"force_p2p", "mcast_ip", "mcast_force", "mcast_port", "type_bx1", "uiport_bx1", "analyze", "mcast_ttl", "id_bx1", "mcast_out",
		"complete", "max_outputs", "on", "password", "host", "latency_bx2", "fec_force", "session", "fec_adaptive",
		"nic", "fec_block", "type_bx2", "snames", "fec_aware", "fec_overhead", "stream", "port", "uiport_bx2", "alias", "id_bx2" ,
		"number_of_streams", "", "testid"}, 
		
		new String[] { userName_bx1,  userName_bx2,  userPass_bx1,  userPass_bx2,  login_ip_bx1, 
		 login_ip_bx2,  latency_bx1,  time_shift , force_p2p,  mcast_ip,  mcast_force,  mcast_port, 
		 type_bx1,  uiport_bx1,  analyze,  mcast_ttl,  id_bx1,  mcast_out,
		 complete,  max_outputs,  on,  password,  host,  latency_bx2,  fec_force,  session, 
		 fec_adaptive,  nic,  fec_block,  type_bx2,  snames,  fec_aware,  fec_overhead,  stream, 
		 port,  uiport_bx2,  alias,  id_bx2,  number_of_streams, mixed, testid });
		
		driverReslut = ((BroadcasterMultiplePushDriver) testDriver).testIMPLManual(
		userName_bx1, userName_bx2, userPass_bx1, userPass_bx2, login_ip_bx1, login_ip_bx2, latency_bx1, time_shift,
		force_p2p, mcast_ip, mcast_force, mcast_port, type_bx1, uiport_bx1, analyze, mcast_ttl, id_bx1, mcast_out,
		complete, max_outputs, on, password, host, latency_bx2, fec_force, session, fec_adaptive,
		nic, fec_block, type_bx2, snames, fec_aware, fec_overhead, stream, port, uiport_bx2, alias, id_bx2 ,
		number_of_streams, mixed);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp1 = new Timestamp(System.currentTimeMillis());
		long 		timeStemp1 = timestamp1.getTime() ;
		connecttoDb(login_ip_bx1, Integer.parseInt(tmpStartMemHolder.substring(0, tmpStartMemHolder.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp1);
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx2,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp2 = new Timestamp(System.currentTimeMillis());
		long 		timeStemp2 = timestamp2.getTime() ;
		connecttoDb(login_ip_bx2, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp2);
		
		// The actual test method.
		Assert.assertEquals(driverReslut.getResult(), "pass");
		// Checking if broadcaster has crashes while execution of the test.
		// This is special case because of here a two broadcaster are involved.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx1,  "22",  "pidof zixi_broadcaster"));
		Assert.assertEquals(sutProcessId2, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_bx2,  "22",  "pidof zixi_broadcaster"));
	} 
}
