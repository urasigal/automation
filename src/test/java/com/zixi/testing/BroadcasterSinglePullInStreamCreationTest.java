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
	private StreamEntity streamEntity;
	private Properties prop = null;
	private InputStream input = null;
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
		try 
		{
			input = new FileInputStream(propertiesFile);
			prop = new Properties();
			prop.load(input);
			streamEntity = new StreamEntity(prop.getProperty("width"),prop.getProperty("hight"),
			prop.getProperty("progressivness"), prop.getProperty("fps"), 
			prop.getProperty("audiobitrate"),prop.getProperty("videocodec"),
			prop.getProperty("audiocodec"));
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Test description that will be written to report HTML.
		Reporter.log("Stream parameters as follows:");
		Reporter.log("Resolution is: " +streamEntity.getWidth() + "*" + streamEntity.getHight() );
		Reporter.log("Interlacing type is:" + streamEntity.getProgressive());
		Reporter.log("FPS is:" + streamEntity.getFps());
		Reporter.log("Video codec is:"+streamEntity.getVideoCodec());
		Reporter.log("Audio codec is:"+streamEntity.getAudioCodec());
		Reporter.log("Audio bitrate is:"+streamEntity.getAudioRate());
		
		Assert.assertNotNull(((BroadcasterSinglePullInStreamCreationDriver) testDriver).testIMPL(userName, userPass, Host, loin_ip, id,source, uiport, pull_port, latency, fec_latency, fec_overhead, mcast_force, time_shift, nic, max_outputs, type, password, mcast_port, complete, mcast_ip ,fec_adaptive, mcast_ttl, on, func, fec_force, mcast_out));
	}
}
