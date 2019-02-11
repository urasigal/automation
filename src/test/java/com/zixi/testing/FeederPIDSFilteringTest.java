package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FeederPIDSFilteringDriver;


/*  This class is intended to group a tests that are verifying the Feeder's PID filtering feature.
	Zixi Feeder has as its main functionality an ability to accept MPEG transport stream. In the MPEG transport stream has a concept of programs.
	Zixi feeder is able to filter elementry streams within a particular program by elementry stream's ID - preserve or remove elementry streams.
*/

public class FeederPIDSFilteringTest extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new FeederPIDSFilteringDriver(); }

	@Parameters({"userPass", "userName", "login_ip", "uiport", "streamname", "mip", "udp_ip", "pids", "testid"})
	@Test
	public void getSptsPids(String userPass, String userName, String login_ip,
	String uiport, String streamname, String mip, String udp_ip, String pids, String testid) throws Exception {
	productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder");
		driverReslut = ((FeederPIDSFilteringDriver) testDriver).compareToGivenParametersElementryPidsFromFeeder(userPass, userName, login_ip, uiport, streamname, mip, udp_ip, pids);
		
		//Assert.assertEquals( driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_feeder"));
	}
}
