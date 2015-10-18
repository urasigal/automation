package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcasterUdpInStreamCreationTest {

	private TestDriver testDriver;
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterSingleUdpInCreationDriver();
	}

	@Parameters({ "userName","userPass","loin_ip","ts_port","id","rtp_type","multi_src","max_bitrate",
		"time_shift","mcast_ip","mcast_force","mcast_port","nic","type","multicast","enc_key","kompression",
		"uiport","mcast_ttl","enc_type","mcast_out","complete","max_outputs","on"})
	@Test
	public void broadcasterPullInCreation(  String userName, String userPass,
											String loin_ip, String ts_port, String id,String rtp_type,String multi_src, String max_bitrate,
											String time_shift,String mcast_ip,String mcast_force, String mcast_port, String nic,String type,String multicast,
											String enc_key, String kompression,
											String uiport,String mcast_ttl,String enc_type,String mcast_out,String complete,String max_outputs,String on) throws InterruptedException 
	{
		 Assert.assertEquals(((BroadcasterSingleUdpInCreationDriver) testDriver).testIMPL( userName,  userPass, 
		 loin_ip,  ts_port,  id, rtp_type, multi_src,  max_bitrate,
		 time_shift, mcast_ip, mcast_force,  mcast_port,  nic, type, multicast,
		 enc_key,  kompression,
		 uiport, mcast_ttl, enc_type, mcast_out, complete, max_outputs, on),"Stream " + "'"+ id +"'"+ " added.");
	}
}
