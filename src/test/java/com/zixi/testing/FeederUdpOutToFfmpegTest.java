package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.*;
import com.zixi.drivers.drivers.FeederUdpOutToFfmpegDriver;

public class FeederUdpOutToFfmpegTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederUdpOutToFfmpegDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "name", "mip","port", "ip", "prog", "chan", "oh", "op", "onic", "ottl", "osmooth" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String name, String mip,
	String port, String ip, String prog, String chan, String oh, String op, String onic, String ottl, String osmooth,String testid) throws Exception {
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "name", "mip", "port", "ip", "prog", "chan", "oh",
		"op", "onic", "ottl", "osmooth" ,"testid"}, 
		new String[] { userName, userPass, login_ip, uiport, name, mip, port, ip, prog, chan, oh, op, onic, ottl, osmooth ,testid });
		
		driverReslut = ((FeederUdpOutToFfmpegDriver) testDriver).testIMPL( userName,  userPass,  login_ip,  uiport,  name,  mip, port,  ip,  prog,  chan,  oh,
		op,  onic,  ottl,  osmooth);
		
		Assert.assertEquals(driverReslut.getResult(), "UDP output added.");
	}

}
