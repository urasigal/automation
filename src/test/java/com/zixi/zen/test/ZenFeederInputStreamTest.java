package com.zixi.zen.test;

import java.util.LinkedList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.FeederInputStreamDeletionDriver;
import com.zixi.drivers.drivers.FeederSettingsDriver;
import com.zixi.drivers.drivers.ZenFeedersData;
import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenFeederInputStreamTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new FeederInputStreamDeletionDriver(); }
	
	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"feederUserName", "feederUserPass", "feederLogin_ip", "feederUiport", "zenUserName", "zenUserPass", "zenHost", "zenUiport", "feederName", "testid"})
	@Test
	public void checkZenFeederInputStreams(String feederUserName, String feederUserPass, String feederLogin_ip, String feederUiport, String zenUserName, String zenUserPass,
		String zenHost, String zenUiport, String feederName, String testid) throws Exception {
		buildTestParametersString(new String[] { "feederUserName", "feederUserPass", "feederLogin_ip", "feederUiport", "testid"}, 
		new String[] { feederUserName, feederUserPass, feederLogin_ip, feederUiport, zenUserName, zenUserPass, zenHost, zenUiport, feederName, testid });
		org.json.JSONArray feedersInputs 	= new org.json.JSONArray( ((FeederInputStreamDeletionDriver ) testDriver).feederGetInputStreamsNames(feederUserName, feederUserPass, feederLogin_ip, feederUiport).getResult());
		org.json.JSONArray zenFeedersInputs = new org.json.JSONArray(ZenFeedersData.getZenFeederInputStreams (zenUserName, zenUserPass, zenHost, zenUiport, feederName));
		
		LinkedList<String> zenFeederInputStreamName=new LinkedList<String>();
		for(int i = 0; i < zenFeedersInputs.length(); i++)
		{
			zenFeederInputStreamName.add(zenFeedersInputs.getString(i));
		}
		
		for(int i =0; i < feedersInputs.length(); i++)
		{
			if(zenFeederInputStreamName.contains(feedersInputs.get(i)))
			{
				zenFeederInputStreamName.remove(i);
			}
		}
		Assert.assertEquals(zenFeederInputStreamName.size(), 0); 
	}
}