package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.AwsConnectorDriver;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;

public class BroadcasterS3HlsUploadingTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		// This is a test driver.
		testDriver = new AwsConnectorDriver();
	}

	// This test case defines HLS output stream to AWS s3 bucket.
	@Parameters( { 
		"userName", 		// broadcaster user name
		"userPass",			//broadcaster user pass
		"login_ip", 			//broadcaster user IP
		"uiport", 				// broadcaster UI port
		"output_name",	// ouptut stream name
		"matrix", 				// matrix flag on/off
		"stream",				 // name of source stream
		"type", 					// stream type
		"url",						// URL on AWS s3 to upload HLS files
		"cleanup",				// if delete outdated HLS files from AWS s3.
		"region",				// AWS region
		"encap",				// encapsulation tupe HLS/DASH
		"no_tls",				// ?
		"upload_type",		// ?
		"testid"					// Internal test id
		})
	@Test
	public void broadcasterUploadHlsToS3(String userName, String userPass, String login_ip, String uiport,
	String output_name, String matrix, String stream, String type, String url, String cleanup, String region,
	String  encap, String no_tls, String upload_type, String testid) throws Exception {
		
		buildTestParametersString(new String[] {userName, userPass, login_ip, uiport,
		output_name, matrix, stream, type, url, cleanup, region, encap, no_tls, upload_type, "testid" }, 
		new String[] {userName, userPass, login_ip, uiport,
				output_name, matrix, stream, type, url, cleanup, region, encap, no_tls, upload_type, testid });
		
		driverReslut = ((AwsConnectorDriver) testDriver).uploadHlsToS3( userName, userPass, login_ip, uiport,
		output_name, matrix, stream, type, url, cleanup, region, encap, no_tls, upload_type);
		
		Assert.assertEquals(driverReslut.getResult(), "Output " + output_name  + " added.");
	}
}
