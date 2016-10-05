package com.zixi.threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.zixi.drivers.drivers.*;

public class Ztask implements Callable<String> {

	private TestDriver driver;
	private ArrayList<String> parameters = null;
	
	public Ztask(TestDriver driver, ArrayList<String> parameters)
	{
		this.driver = driver;
		this.parameters = parameters;
	}
	
	public String call() throws Exception 
	{
		String results = ((BroadcasterSinglePullInStreamCreationDriver)driver).testIMPL(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), 
		parameters.get(5), parameters.get(6), parameters.get(7), parameters.get(8), parameters.get(9), parameters.get(10), parameters.get(11), parameters.get(12), 
		parameters.get(13), parameters.get(14), parameters.get(15), parameters.get(16),parameters.get(17), parameters.get(18), parameters.get(19), parameters.get(20), 
		parameters.get(21), parameters.get(22), parameters.get(23), parameters.get(24),parameters.get(25), parameters.get(26), parameters.get(27), parameters.get(28));
		
		return results;
	}
}
