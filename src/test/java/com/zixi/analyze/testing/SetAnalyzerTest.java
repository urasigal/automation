package com.zixi.analyze.testing;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterAnalyzerDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class SetAnalyzerTest extends BaseTestZixiMainComponents
{
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterAnalyzerDriver(); }
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "analyze", "testid" })
	@Test
	public void broadcasterTurnOnAnalyzerOnAllStreams(String login_ip, String userName, String userPassword, 
	String uiport, String analyze, String testid) throws Exception {
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "analyze", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, analyze, testid});
		
		driverReslut = ((BroadcasterAnalyzerDriver) testDriver).turnAnalyzerOnAllInputs( userName,  userPassword,  login_ip,  uiport,  analyze);
			
		Assert.assertEquals(driverReslut.getResult(), "analyzed");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "analyze", "testid" })
	@Test
	public void feederTurnOnAnalyzerOnAllStreams(String login_ip, String userName, String userPassword, 
	String uiport, String analyze, String testid) throws Exception {
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "analyze", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, analyze, testid});
		
		driverReslut = ((BroadcasterAnalyzerDriver) testDriver).feederTurnAnalyzerOnAllInputs( userName,  userPassword,  login_ip,  uiport,  analyze);
			
		Assert.assertEquals(driverReslut.getResult(), "analyzed");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	}
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "refstream", "testid" })
	@Test
	public void broadcasterCompareContinuityCounterOfReferenceStreamToRestStreamsTest(String login_ip, String userName, String userPassword, 
	String uiport, String refstream, String testid) throws Exception {
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "refstream", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, refstream, testid});
		
		driverReslut = ((BroadcasterAnalyzerDriver) testDriver).boadcasterCompareStatisticOnAllInputsStreams( userName,  userPassword,  login_ip,  uiport,  refstream);
			
		Assert.assertEquals(driverReslut.getResult(), "passed");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
	
	//Compare CC errors on input reference stream to a set of input streams.
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "streams", "ref_stream", "testid" })
	@Test
	public void feederCompareContinuityCounterOfReferenceStreamToRestStreamsTest(String login_ip, String userName, String userPassword,
	String uiport, String streams, String ref_stream, String testid) throws Exception
	{
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "streams", "ref_stream", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, streams, ref_stream, testid});
		
		driverReslut = ((BroadcasterAnalyzerDriver) testDriver).feederCompareStatisticCcErrors( login_ip, userName, userPassword, uiport, streams, ref_stream);
		
		Assert.assertEquals(driverReslut.getResult(), "passed");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_feeder"));
				
	}
	
	
	// Detect "frozen frame stream"
	// This test will see at stream's statistics and search for frozen counter field.
	// The user has to decide what a particular number in the frozen field. 
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "streamName", "frozenCounter", "streamLenthSec", "testid" })
	@Test
	public void broadcasterFrozenTest(String login_ip, String userName, String userPassword, 
	String uiport, String streamName, String frozenCounter, String streamLenthSec, String testid) throws Exception {
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "streamName", "frozenCounter", "streamLenthSec", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, streamName, frozenCounter, streamLenthSec, testid});
		
		driverReslut = ((BroadcasterAnalyzerDriver) testDriver).boadcasterFozenDetection( userName,  userPassword,  login_ip,  uiport,  streamName, frozenCounter, streamLenthSec);
			
		Assert.assertEquals(driverReslut.getResult(), "passed");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
