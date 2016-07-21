package com.zixi.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.zixi.drivers.TestDriver;

public class ZthreadPool
{
	private ExecutorService executorService = null; 
	ArrayList<String> parameters = null;
	private TestDriver driver    = null;
	
	public ZthreadPool(int numOfThreads, ArrayList<String> parameters, TestDriver driver)
	{
		executorService = Executors.newFixedThreadPool(numOfThreads); 
		this.parameters = parameters;
		this.driver = driver;
	}

	public void zexecute() throws InterruptedException, ExecutionException
	{
		ArrayList<Callable<String>> callablesZtasks = new ArrayList<Callable<String>>();
		int parameterSize = parameters.size();
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		for(int i = 0 ; i < counter; i++)
		{
			parameters.set(4,  "abc" + i);
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			Ztask ztask = new Ztask(driver, tempParameters);
			callablesZtasks.add(ztask);
		}
		List<Future<String>> futures = executorService.invokeAll(callablesZtasks);
		
		for(Future future : futures)
		{
			System.out.println((String)future.get());
		}
	}
}
