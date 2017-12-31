package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;
import com.zixi.drivers.drivers.FeederOutputPushToBxDriver;
import com.zixi.drivers.tools.DriverReslut;

public class FeederOutputPushToBxTest extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new FeederOutputPushToBxDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "name", "mip", "port", "ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
	"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force", "fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
	"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr", "rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded","uiport" ,"testid"})
	@Test
	public void feederOutputToBxTest(String userName, String userPass, String login_ip, String name, String mip,
		String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp, String oeky, String obit,
		String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive, String ofec, String ofecl,
		String stop_on_drop, String mmt, String smoothing, String limited, String minbps, String lim_enc_addr, String pad_to_cbr,
		String rtmp_feedback, String ohst, String oprt, String onic, String oalt,String bonded, String uiport,String testid) throws Exception {
		
		// Save test parameters in order to pass them to testLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port",
		"ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky", "obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force",
		"fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt", "smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr",
		"rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded","uiport" ,"testid"}, 
		new String[] { userName, userPass, login_ip, name, mip, port, ip, prog, chan, type, ostr, oses, oetp, oeky,
		obit, olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt,
		smoothing, limited, minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst, oprt, onic, oalt,bonded,uiport ,testid });
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		
		driverReslut = ((FeederOutputPushToBxDriver) testDriver).testIMPL(userName, userPass, login_ip, name, mip, port,
		ip, prog, chan, type, ostr, oses, oetp, oeky, obit, olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive,
		ofec, ofecl, stop_on_drop, mmt, smoothing, limited, minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst,
		oprt, onic, oalt,bonded, uiport).getResultObj();
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Broadcaster output added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	} // End of test.
	
	@Parameters({ "userName", "userPass", "login_ip", "name", "mip", "port", "ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
	"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force", "fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
	"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr","rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded", "rtmp_stream",
	"rtmp_url", "rtmp_user", "rtmp_pass", "rtmp_url2", "rtmp_hot","uiport" ,"testid"})
	@Test
	public void feederOutputToBxTestAutomaticRtmp(String userName, String userPass, String login_ip, String name, String mip,
	String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp, String oeky, String obit,
	String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive, String ofec, String ofecl,
	String stop_on_drop, String mmt, String smoothing, String limited,String minbps, String lim_enc_addr, String pad_to_cbr,
	String rtmp_feedback, String ohst, String oprt, String onic, String oalt,String bonded, String rtmp_stream,String rtmp_url ,
	String rtmp_user,String rtmp_pass, String rtmp_url2, String rtmp_hot,  String uiport,String testid) throws Exception {
	
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port", "ip", "prog", "chan", "type", "ostr", "oses", "oetp", "oeky",
		"obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force", "fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
		"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr", "rtmp_feedback", "ohst", "oprt", "onic", "oalt","bonded", "rtmp_stream",
		"rtmp_url", "rtmp_user", "rtmp_pass", "rtmp_url2", "rtmp_hot","uiport" ,"testid"}, 
		new String[] { userName, userPass, login_ip, name, mip, port, ip, prog, chan, type, ostr, oses, oetp, oeky,
		obit, olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt, smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,
		rtmp_feedback, ohst, oprt, onic, oalt,bonded, rtmp_stream, rtmp_url, rtmp_user, rtmp_pass, rtmp_url2, rtmp_hot ,uiport ,testid });
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		// Execute a test
		driverReslut = ((FeederOutputPushToBxDriver) testDriver).testIMPL(userName, userPass, login_ip, name, mip, port, ip, prog, chan, type, ostr, oses, oetp, oeky, obit,
		olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt, smoothing, limited, minbps, lim_enc_addr, pad_to_cbr, rtmp_feedback, ohst,
		oprt, onic, oalt,bonded,rtmp_stream, rtmp_url, rtmp_user, rtmp_pass, rtmp_url2, rtmp_hot, uiport).getResultObj();		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(),"Broadcaster output added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	} // End of test.
	
	// This test case is intended to test a bonded feeder push output stream.
	@Parameters({"userName", "userPass", "login_ip", "name", "mip", "port","ip", "prog", "chan", "type", "ostr", "oses", "oetp", 
		"oeky","obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force","fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
		"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr","rtmp_feedback", "group", "bonded", "bond_host1",
		"bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2", "bond_limit2", "bond_backup2" ,"uiport" ,"testid"})
	@Test
	public void feederBondedPushOutputCreationTest(String userName, String userPass, String login_ip, String name, String mip, String port, String ip, 
		String prog, String chan, String type, String ostr, String oses, String oetp, 
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive, String ofec, 
		String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps, String lim_enc_addr, 
		String pad_to_cbr, String rtmp_feedback, String group, String bonded, String bond_host1,
		String bond_port1, String bond_nic1, String bond_limit1, String bond_backup1, String bond_host2, String bond_port2, 
		String bond_nic2, String bond_limit2, String bond_backup2 ,String uiport, String testid) throws Exception{
		
		// Get SUT's PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		// Save test parameters in order to pass them to testLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port","ip", "prog", "chan", "type", "ostr", "oses", "oetp", 
		"oeky","obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force","fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
		"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr","rtmp_feedback", "group", "bonded", "bond_host1",
		"bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2", "bond_limit2", "bond_backup2" ,"uiport" ,"testid"}, 
		
		new String[] { userName, userPass, login_ip, name, mip, port, ip, prog, chan, type, ostr, oses, oetp, 
		oeky, obit, olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt,
		smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,rtmp_feedback, group, bonded, bond_host1,
		bond_port1, bond_nic1, bond_limit1, bond_backup1, bond_host2, bond_port2, bond_nic2, bond_limit2, bond_backup2, uiport, testid});
		
		driverReslut = ( (FeederOutputPushToBxDriver) testDriver).testIMPL( userName,  userPass,  login_ip, name,  mip,  port, 
		ip,  prog,  chan,  type,  ostr,  oses,  oetp, oeky,  obit,  olat,  ofc,  ocmp,  oold,  onfec,  fec_force,  fec_adaptive,ofec,  ofecl,  stop_on_drop, 
		mmt,  smoothing,  limited,  minbps, lim_enc_addr,  pad_to_cbr,  rtmp_feedback, group,  bonded,  bond_host1,  bond_port1,  bond_nic1,  bond_limit1,  bond_backup1, 
		bond_host2,  bond_port2,  bond_nic2,  bond_limit2,  bond_backup2 , uiport);		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(),"Broadcaster output added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	}
	
	@Parameters({"userName", "userPass", "login_ip", "name", "mip", "port","ip", "prog", "chan", "type", "ostr", "oses", "oetp", 
	"oeky","obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force","fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
	"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr","rtmp_feedback", "group", "bonded", "bond_host1",
	"bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2", "bond_limit2", "bond_backup2",  "bond_host3",  "bond_port3", 
	"bond_nic3",  "bond_limit3",  "bond_backup3", "uiport" ,"testid"})
	@Test
	public void feederBondedPushOutputCreationThreeLinksTest(String userName, String userPass, String login_ip, String name, String mip, String port, String ip, 
		String prog, String chan, String type, String ostr, String oses, String oetp, 
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive, String ofec, 
		String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps, String lim_enc_addr, 
		String pad_to_cbr, String rtmp_feedback, String group, String bonded, String bond_host1,
		String bond_port1, String bond_nic1, String bond_limit1, String bond_backup1, String bond_host2, String bond_port2, 
		String bond_nic2, String bond_limit2, String bond_backup2, String bond_host3, String bond_port3, 
		String bond_nic3, String bond_limit3, String bond_backup3, String uiport, String testid) throws Exception
	{
		
		// Get SUT's PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		// Save test parameters in order to pass them to testLink.
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "name", "mip", "port","ip", "prog", "chan", "type", "ostr", "oses", "oetp", 
		"oeky","obit", "olat", "ofc", "ocmp", "oold", "onfec", "fec_force","fec_adaptive", "ofec", "ofecl", "stop_on_drop", "mmt",
		"smoothing", "limited", "minbps", "lim_enc_addr", "pad_to_cbr","rtmp_feedback", "group", "bonded", "bond_host1",
		"bond_port1", "bond_nic1", "bond_limit1", "bond_backup1", "bond_host2", "bond_port2", "bond_nic2", "bond_limit2", "bond_backup2",  "bond_host3",  "bond_port3", 
		"bond_nic3",  "bond_limit3",  "bond_backup3", "uiport" ,"testid"}, 
		new String[] { userName, userPass, login_ip, name, mip, port, ip, prog, chan, type, ostr, oses, oetp, 
		oeky, obit, olat, ofc, ocmp, oold, onfec, fec_force, fec_adaptive, ofec, ofecl, stop_on_drop, mmt,
		smoothing, limited, minbps, lim_enc_addr, pad_to_cbr,rtmp_feedback, group, bonded, bond_host1,
		bond_port1, bond_nic1, bond_limit1, bond_backup1, bond_host2, bond_port2, bond_nic2, bond_limit2, bond_backup2,  bond_host3,  bond_port3, 
		bond_nic3,  bond_limit3,  bond_backup3, uiport, testid});
		
		driverReslut = ((FeederOutputPushToBxDriver) testDriver).testIMPL( userName,  userPass,  login_ip, name,  mip,  port,  ip,  prog,  chan,  type,  ostr,  oses,  oetp,
		oeky,  obit,  olat,  ofc,  ocmp,  oold,  onfec,  fec_force,  fec_adaptive,ofec,  ofecl,  stop_on_drop,  mmt,  smoothing,  limited,  minbps,
		lim_enc_addr,  pad_to_cbr,  rtmp_feedback, group,  bonded,  bond_host1,  bond_port1,  bond_nic1,  bond_limit1,  bond_backup1, 
		bond_host2,  bond_port2,  bond_nic2,  bond_limit2,  bond_backup2, bond_host3,  bond_port3, 
		bond_nic3,  bond_limit3,  bond_backup3, uiport);		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_feeder` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(),"Broadcaster output added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	}
}
