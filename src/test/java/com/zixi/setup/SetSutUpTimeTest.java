package com.zixi.setup;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcasterFileInputDriver;
import com.zixi.drivers.drivers.TestDriver;
import com.zixi.drivers.setup.SetSutUpTimeDriver;
import com.zixi.testing.BaseTest;

public class SetSutUpTimeTest{
	private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() {
		testDriver = new SetSutUpTimeDriver();
	}
	
	@Parameters({"bx_1", "bx_2", "fx_3", "rx_4", "setUpFileLocation"})
	@Test
	public void setSutLocation(String bx_1, String bx_2, String fx_3, String rx_4, String setUpFileLocation) throws Exception {
		Assert.assertEquals( ( (SetSutUpTimeDriver) testDriver).fillSetUpFileWithSutIps( bx_1,  bx_2,  fx_3, rx_4, setUpFileLocation).getResult(), "pass" );
	}
	
	@Parameters({"setUpFileLocation"})
	@Test
	public void checkUptime(String setUpFileLocation) throws Exception {
		Assert.assertEquals( ( (SetSutUpTimeDriver) testDriver).checkUptime(setUpFileLocation).getResult(), "pass" );
	}
	
	
	@Parameters({"setUpFileLocation", "upTimeFileLocation"})
	@Test
	public void setUptime(String setUpFileLocation, String upTimeFileLocation) throws Exception {
		Assert.assertEquals( ( (SetSutUpTimeDriver) testDriver).uptimeSet(setUpFileLocation, upTimeFileLocation), "pass" );
	}
	
	@Test
	public void checkContinuousUptime() throws Exception {
		Assert.assertEquals( ( (SetSutUpTimeDriver) testDriver).continuousUpTimeCheck(), "" );
	}
}
