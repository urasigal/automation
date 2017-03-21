package com.zixi.testing;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterFileUploadDriver;

public class FileTransferTest extends BaseTest{
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterFileUploadDriver(); }

	@Parameters({"destinationServerIP", "destinationServerPort" ,"fiel_upload_mode", "testid"})
	@Test
	public void fileTransfer(String destinationServerIP, String destinationServerPort, String fiel_upload_mode, String testid) throws InterruptedException, IOException 
	{ 
		buildTestParametersString(new String[] {"destinationServerIP", "destinationServerPort", "fiel_upload_mode", "testid"}, 
		new String[] {destinationServerIP, destinationServerPort, fiel_upload_mode, testid });
		
		driverReslut = ((BroadcasterFileUploadDriver) testDriver).testIMPL(destinationServerIP, destinationServerPort, fiel_upload_mode);
		
		Assert.assertEquals(driverReslut.getResult(), "good");	
	}
}