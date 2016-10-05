package com.zixi.testing;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.drivers.BroadcasterFileUploadDriver;

public class FileTransferTest extends BaseTest{
	@BeforeClass
	public void testInit() {
		testDriver = new BroadcasterFileUploadDriver();
	}

	// destinationServerIP   - IP address of the server against which the test should work.
	// destinationServerPort - port number of the server against which the test should work.
	// fiel_upload_mode      - this parameter specifies the test function mode: 1 - file upload, 2 - file download. 
	// testid                - test id - test link stuff. 
	
	@Parameters({"destinationServerIP", "destinationServerPort" ,"fiel_upload_mode", "testid"})
	@Test
	public void fileTransfer(String destinationServerIP, String destinationServerPort, String fiel_upload_mode, String testid) throws InterruptedException, IOException 
	{ 
		this.testid = testid;
		
		testParameters = buildTestParametersString(new String[] {"destinationServerIP", "destinationServerPort", "fiel_upload_mode", "testid"}, 
												   new String[] {destinationServerIP, destinationServerPort, fiel_upload_mode, testid });
		Assert.assertEquals(((BroadcasterFileUploadDriver) testDriver).testIMPL(destinationServerIP, destinationServerPort, fiel_upload_mode), "good");	
	}
}