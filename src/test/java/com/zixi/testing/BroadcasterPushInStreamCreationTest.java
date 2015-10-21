package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;

public class BroadcasterPushInStreamCreationTest {
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterPushInStreamCreationDriver();
	}

	@Parameters({"userName","userPass","login_ip","latency","time_shift","force_p2p","mcast_ip","mcast_force","mcast_port","type","uiport","analyze","mcast_ttl","id","mcast_out","complete","max_outputs","on","password"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,String userPass,String login_ip,String latency,String time_shift,String force_p2p,String mcast_ip,String mcast_force,
			String mcast_port,String type,String uiport,String analyze,String mcast_ttl,String id,
			String mcast_out,String complete,String max_outputs,String on,String password) throws InterruptedException 
	{
		Assert.assertEquals(((BroadcasterPushInStreamCreationDriver) testDriver).testIMPL(userName,userPass,login_ip,latency,
				time_shift,force_p2p,mcast_ip,mcast_force,mcast_port,type,uiport,analyze,mcast_ttl,id,mcast_out,complete,
				max_outputs,on, password), "Stream " + "'"+ id +"'"+ " added.");
	}
}
