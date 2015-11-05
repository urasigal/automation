package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcasterRtmpInputTest {
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterRtmpInCreationDriver();
	}

	@Parameters({ "userName","userPass","login_ip","rtmp_nulls","id","rtmp_url" ,"rtmp_name","time_shift","mcast_ip" ,"mcast_force","mcast_port","type","rtmp_user" ,"rtmp_bitrate","rtmp_passwd","uiport","mcast_ttl","rtmp_latency","mcast_out","complete","max_outputs","on"})
	@Test
	public void broadcasterRtmpPullTest(String userName,String userPass,String login_ip,String rtmp_nulls,String id,String rtmp_url ,String rtmp_name,String time_shift,
			String mcast_ip, String mcast_force,String mcast_port,String type,String rtmp_user, 
			String rtmp_bitrate,String rtmp_passwd,String uiport,
			String mcast_ttl,String rtmp_latency,String mcast_out,String complete,String max_outputs,String on) throws InterruptedException 
	{
		 Assert.assertEquals(((BroadcasterRtmpInCreationDriver) testDriver).testIMPL(userName, userPass, login_ip, rtmp_nulls, id, rtmp_url, 
				 rtmp_name, time_shift, mcast_ip, mcast_force, mcast_port,
				 type, rtmp_user, rtmp_bitrate, rtmp_passwd, uiport, mcast_ttl,
				 rtmp_latency, mcast_out, complete, max_outputs, on),"Stream " + "'"+ id +"'"+ " added.");
	}

}
