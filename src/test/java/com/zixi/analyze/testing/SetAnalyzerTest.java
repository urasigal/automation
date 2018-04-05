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
}
