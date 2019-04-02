package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcasterAddTranscoderProfileDriver;
import com.zixi.drivers.drivers.SSIMVideoAssessmentDriver;

public class SSIMVideoAssessmentTest  extends BaseTestZixiMainComponents {
 
	@BeforeClass
	public void testInit() { 
		testDriver = new SSIMVideoAssessmentDriver();
	} 

	@Parameters({"sourceStreamUdpPort", "testedStreamUdpPort", "fileRecordInterval",  "cropHight",  "cropWidth", "testid" }) 
	@Test
	public void transcoderSsimEval(String sourceStreamUdpPort, String testedStreamUdpPort, String fileRecordInterval, String cropHight, String cropWidth, String testid) throws Exception {
				System.out.println("Test started");
		buildTestParametersString(new String[] {"sourceStreamUdpPort", "testedStreamUdpPort", "fileRecordInterval", "cropHight",  "cropWidth", "testid"}, 
		new String[] {sourceStreamUdpPort, testedStreamUdpPort, fileRecordInterval,  cropHight,  cropWidth, testid}); 
		
		//testDriver = new SSIMVideoAssessmentDriver();
		double result =  ( (SSIMVideoAssessmentDriver) testDriver ) .
		ssim_evaluation( Integer.parseInt( sourceStreamUdpPort ), Integer.parseInt( testedStreamUdpPort ), Integer.parseInt( fileRecordInterval ), Integer.parseInt(  cropHight), Integer.parseInt( cropWidth ));
		System.out.println("Reslult is >>>> " + result);
		Assert.assertTrue(result >= 0.9);
	}
	
//	public static void main(String[] args) throws Exception {
//		SSIMVideoAssessmentTest sSIMVideoAssessmentTest = new SSIMVideoAssessmentTest();
//		sSIMVideoAssessmentTest.broadcasterSingleInputStreamstatisticAnilyzer( "5577", "8899", "40000", "1040", "1888",  "555");
//	}
}