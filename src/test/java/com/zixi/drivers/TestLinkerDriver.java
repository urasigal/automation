package com.zixi.drivers;

import static com.zixi.globals.Macros.ADD_TRANSCODER_PROFILE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.TestlinkIntegration;

public class TestLinkerDriver extends
BroadcasterLoggableApiWorker implements TestDriver
{
	public String testIMPL()  {
	FileInputStream fis;
	BufferedReader br;
	try {
		fis = new FileInputStream("src/main/resources/tests");
	
 
	//Construct BufferedReader from InputStreamReader
	br = new BufferedReader(new InputStreamReader(fis));
	TestlinkIntegration tl = new TestlinkIntegration();
	String line = null;
	
	while ((line = br.readLine()) != null) {
		tl.setResult(line,ExecutionStatus.BLOCKED,this.getClass().getCanonicalName());
	}
	br.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "success";
	}
}
