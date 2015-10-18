package com.zixi.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;

public class BroadcasterSinglePullInStreamCreationTest {

	private TestDriver testDriver;
	@BeforeClass
	public void testInit() {
		
			
			testDriver = new BroadcasterSinglePullInStreamCreationDriver();
		
	}

	@Parameters({ "userName","userPass","Host","loin_ip","id","source","uiport","pull_port","latency",
				"fec_latency","fec_overhead","mcast_force","time_shift","nic","max_outputs",
				"type","password","mcast_port","complete","mcast_ip","fec_adaptive","mcast_ttl",
				"on","func","fec_force","mcast_out","propertiesFile"})
	@Test
	public void broadcasterPullInCreation(String userName, String userPass, String Host,
											String loin_ip, String id,String source, String uiport, String pull_port,
											String latency, String fec_latency,String fec_overhead,String mcast_force,
											String time_shift,String nic,String max_outputs, String type,
											String password,String mcast_port,String complete,
											String mcast_ip,String fec_adaptive, String mcast_ttl,
											String on, String func, String fec_force, String mcast_out, String propertiesFile) throws InterruptedException 
	{
		Assert.assertEquals(((BroadcasterSinglePullInStreamCreationDriver) testDriver).testIMPL(userName, userPass, Host, loin_ip, id,source, 
				uiport, pull_port, latency, fec_latency, fec_overhead, mcast_force, time_shift, 
				nic, max_outputs, type, password, mcast_port, complete, mcast_ip ,fec_adaptive, 
				mcast_ttl, on, func, fec_force, mcast_out, propertiesFile), "Stream " + "'"+ id +"'"+ " added.");
	}
}
