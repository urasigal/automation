package com.zixi.testasutility;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.awsdrivers.Opereation;
import com.zixi.drivers.drivers.AwsConnectorDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class AwsZixiUtilityTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		// This is a test driver.
		testDriver = new AwsConnectorDriver();
	}

	// This test case defines HLS output stream to AWS s3 bucket.
	@Parameters( { 
		"operation_type", 	// What action to perform on AWS S3.	
		"bucketName",			// Bucket name.
		"prefix", 					// Path to folder within a Bucket.
		"testid"						// Internal test id
		})
	@Test
	public void AwsDeleteFromS3(String operation_type,  String bucketName,  String prefix,  String testid) throws Exception {
		buildTestParametersString(new String[] {"operation_type",  "bucketName", "prefix", "testid" }, 
		new String[] {operation_type,  bucketName, prefix, testid });
		HashMap<String, String> params = new HashMap();
		params.put("bucketName", bucketName);
		params.put("prefix", prefix);
		driverReslut = ((AwsConnectorDriver) testDriver).performOperationOnAwsS3(Opereation.valueOf(operation_type), 
								new AwsConnectorDriver.OperationContainer (params));
		Assert.assertEquals(driverReslut.getResult(), "The object assumed to be deleted from AWS s3 bucket");
	}
	
		// This test case defines HLS output stream to AWS s3 bucket.
		@Parameters( { 
			"operation_type", 	// What action to perform on AWS S3.	
			"bucketName",			// Bucket name.
			"prefix", 					// Path to folder within a Bucket.
			"test_duration",
			"file_duration",
			"testid"						// Internal test id
			})
		@Test
		public void AwsCheckUploadingToS3(String operation_type,  String bucketName,  String prefix, String test_duration, String file_duration, String testid) throws Exception {
			buildTestParametersString(new String[] {"operation_type",  "bucketName", "prefix",  "test_duration", "file_duration", "testid" }, 
			new String[] {operation_type,  bucketName, prefix, testid });
			HashMap<String, String> params = new HashMap();
			params.put("bucketName", bucketName);
			params.put("prefix", prefix);
			params.put("test_duration", test_duration);   
			params.put("file_duration", file_duration); 
			
			driverReslut = ((AwsConnectorDriver) testDriver).performOperationOnAwsS3(Opereation.valueOf(operation_type), 
									new AwsConnectorDriver.OperationContainer (params));
			Assert.assertEquals(driverReslut.getResult(), "The object assumed to be deleted from AWS s3 bucket");
		}
	
	
}
