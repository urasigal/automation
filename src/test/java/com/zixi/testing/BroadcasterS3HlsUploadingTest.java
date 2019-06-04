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
	@Parameters( { "userName", "userPass", "login_ip", "uiport",
		"output_name", "matrix", "stream", "type", "url", "cleanup", "region", "encap", "no_tls", "upload_type", "testid"})
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
		
		Assert.assertEquals(driverReslut.getResult(), "good");
	}
}
