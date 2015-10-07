package com.zixi.testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;
import com.zixi.testing.pages.HomePage;

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
		Reporter.log("<h3>Stream parameters as follows:</h2>");
		Reporter.log("<p>Resolution is: " +streamEntity.getWidth() + "*" + streamEntity.getHight() +"</p>");
		Reporter.log("<p>Interlacing type is:" + streamEntity.getProgressive()+ "</p>");
		Reporter.log("<p>FPS is:"+streamEntity.getFps()+"</p>");
		Reporter.log("<p>Video codec is:"+streamEntity.getVideoCodec()+"</p>");
		Reporter.log("<p>Audio codec is:"+streamEntity.getAudioCodec()+"</p>");
		Reporter.log("<p>Audio bitrate is:"+streamEntity.getAudioRate()+"</p>");
		
		Assert.assertNotNull(((BroadcasterSinglePullInStreamCreationDriver) testDriver).testIMPL(userName, userPass, Host, loin_ip, id,source, uiport, pull_port, latency, fec_latency, fec_overhead, mcast_force, time_shift, nic, max_outputs, type, password, mcast_port, complete, mcast_ip ,fec_adaptive, mcast_ttl, on, func, fec_force, mcast_out));
	}
}
