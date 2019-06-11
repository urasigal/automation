package com.zixi.testasutility;

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
		"operation_type", 		
		"bucketName",			
		"key", 			
		"testid"					// Internal test id
		})
	@Test
	public void AwsDeleteFromS3(String operation_type,  String bucketName,  String prefix,  String testid) throws Exception {
		buildTestParametersString(new String[] {"operation_type",  "bucketName", "prefix", "testid" }, 
		new String[] {operation_type,  bucketName, prefix, testid });
		
		driverReslut = ((AwsConnectorDriver) testDriver).performOperationOnAwsS3(Opereation.valueOf(operation_type), 
				new AwsConnectorDriver.OperationContainer (){
				 public void putParam(String bucketName, String prefix)
				 {
					 addToParams("bucketName", bucketName);
					 addToParams("prefix", prefix);
				 }
		} );
		Assert.assertEquals(driverReslut.getResult(), "Output " + " added.");
	}
}
