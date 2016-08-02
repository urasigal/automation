package com.zixi.threads;

import static com.zixi.globals.Macros.UDPMODE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterRtmpInCreationDriver;
import com.zixi.drivers.BroadcasterRtmpPushInputStreamDriver;
import com.zixi.drivers.BroadcasterRtmpPushOutputCreationDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.StreamsDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.testing.BroadcaserSingleOutputStreamDeletionTest;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterInitialSecuredLogin;

public class ZthreadPool
{
	private ExecutorService executorService = null; 
	ArrayList<String> parameters = null;
	private TestDriver driver    = null;
	
	// Constructor method.
	public ZthreadPool(int numOfThreads, ArrayList<String> parameters)
	{
		executorService = Executors.newFixedThreadPool(numOfThreads); 
		this.parameters = parameters;
	}
	
	
	public String executeDeleteAll() throws InterruptedException 
	{
		// Container for the callable tasks.
		ArrayList<Callable<String>> callablesZtasks = new ArrayList<Callable<String>>();
		
		int parameterSize = parameters.size();
		StreamsDriver streamsDriver = new StreamsDriver();
		ArrayList<String> outputIds =  streamsDriver.broadcasterGetOutputStreamsIds(parameters.get(0), parameters.get(3), parameters.get(1), parameters.get(2));
		
		// Create a tasks.
		for (int i = 0; i < outputIds.size(); i++) {
			// Add and create a callable tasks for RTMP output stream creation. 
			
			int index = i;
			callablesZtasks.add(new Callable<String>()
			{
				public String call()
				{ 
					ApiWorkir apiworker = new ApiWorkir();
					//login_ip, userName, userPassword, uiport
					BroadcasterInitialSecuredLogin broadcasterInitialSecuredLogin = new BroadcasterInitialSecuredLogin();
					String responseCookieContainer[] = new String[2];
					responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + parameters.get(0) + ":" + parameters.get(3) + "/login.htm", 
					parameters.get(1) , parameters.get(2), parameters.get(0), parameters.get(3));
					
					return apiworker.sendGet("http://" + parameters.get(0) + ":" + parameters.get(3) +  "/zixi/remove_output.json?id=" + outputIds.get(index), "", UDPMODE, 
					responseCookieContainer, parameters.get(0), this, parameters.get(3));
				}	
			});
		}
		
		List<Future<String>> futuresOutPutStreamDeletion = executorService.invokeAll(callablesZtasks);
		
		return "";
	}
	
	public String executeRtmpPush() throws InterruptedException, ExecutionException {  
		
		// This is a containers for the Callable tasks.
		ArrayList<Callable<String>> callablesZtasks1 = new ArrayList<Callable<String>>();
		ArrayList<Callable<String>> callablesZtasks2 = new ArrayList<Callable<String>>();
		/////////////////////////////////////////////////////////////////////////////////
		
		int parameterSize = parameters.size();
		
		// Gets a number of desired tasks.
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		for(int i = 0 ; i < counter; i++)
		{
			TestDriver driver1 = new BroadcasterRtmpPushOutputCreationDriver();
			TestDriver driver2 = new BroadcasterRtmpPushInputStreamDriver();
					
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			
			tempParameters.set(14,  parameters.get(14) + i);
			tempParameters.set(26,  parameters.get(26) + i); 
			
			// Add and create a callable tasks for RTMP output stream creation. 
			callablesZtasks1.add(new Callable<String>()
			{
				public String call()
				{
					String results = ((BroadcasterRtmpPushOutputCreationDriver)driver1).testIMPL(
					tempParameters.get(0), //login_ipBX1 login_ip
					tempParameters.get(2), //userNameBX1 userName
					tempParameters.get(4), //userPasswordBX1  userPassword
					tempParameters.get(6), //uiportBX1 uiport
					tempParameters.get(8), //typeBX1  type
					tempParameters.get(9),  // name name
					tempParameters.get(10), // stream stream
					tempParameters.get(11), // matrixBX1 matrix
					tempParameters.get(12), // url url
					tempParameters.get(13), // url_alt url_alt
					tempParameters.get(14),  // rtmp_stream rtmp_stream
					tempParameters.get(15),  // user user
					tempParameters.get(16),  // bandwidth bandwidth
					tempParameters.get(17), // latency latency
					tempParameters.get(18), // reconnect reconnect
					tempParameters.get(19), //sendfi  sendfi
					tempParameters.get(20), // disconnect_low_br disconnect_low_br
					tempParameters.get(21), // static_latency static_latency
					tempParameters.get(22), // dec_type dec_type
					tempParameters.get(23), // dec_key dec_key 
					tempParameters.get(24)); // password password
					return results;
				}	
			});
			
			// Add and create a callable tasks for RTMP push input stream creation. 
			callablesZtasks2.add(new Callable<String>()
			{
				public String call()
				{
					
					String results = ((BroadcasterRtmpPushInputStreamDriver)driver2).testIMPL(tempParameters.get(2),tempParameters.get(5), tempParameters.get(1), tempParameters.get(7),
							tempParameters.get(25),tempParameters.get(26),tempParameters.get(27),tempParameters.get(28),tempParameters.get(29),tempParameters.get(30),tempParameters.get(31),
							tempParameters.get(32),
							tempParameters.get(33), tempParameters.get(34), tempParameters.get(35), tempParameters.get(36), tempParameters.get(37),
							tempParameters.get(38), tempParameters.get(39), tempParameters.get(40));
					return results;
				}	
			});
		}
		
		// Execute concurrently all tasks.
		List<Future<String>> futuresOutPutStreamCreation = executorService.invokeAll(callablesZtasks1);
		List<Future<String>> futuresInputStreamCreation  = executorService.invokeAll(callablesZtasks2);
		
		String result;
		int numberOfAddedOutPutStreams = 0;
		int numberOfAddedInputPutStreams = 0;
		
		for(Future<String> future : futuresOutPutStreamCreation)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("added."))
			{
				numberOfAddedOutPutStreams ++;
			}
			
		}
		
		for(Future<String> future : futuresInputStreamCreation)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("added."))
			{
				numberOfAddedInputPutStreams ++;
			}
			
		}
		
		if (numberOfAddedInputPutStreams == counter && numberOfAddedOutPutStreams == counter)
			return "pass";
		else
			return "failed";
	}
	
	
	
	
	public String zexecutetRtmpPull() throws InterruptedException, ExecutionException
	{
		// This is a container for the Callable tasks.
		ArrayList<Callable<String>> callablesZtasks = new ArrayList<Callable<String>>();
		/////////////////////////////////////////////////////////////////////////////////
		int parameterSize = parameters.size();
		
		// Gets a number of desired tasks.
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		for(int i = 0 ; i < counter; i++)
		{
			driver = new BroadcasterRtmpInCreationDriver();
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			tempParameters.set(4,  parameters.get(4) + i); 
			
			// Add and create a callable tasks. 
			callablesZtasks.add(new Callable<String>()
			{
				public String call()
				{
//					userName, userPass, login_ip, rtmp_nulls, id, rtmp_url, rtmp_name, time_shift, mcast_ip, mcast_force, mcast_port, type,
//					rtmp_user, rtmp_bitrate, rtmp_passwd, uiport, mcast_ttl, rtmp_latency, mcast_out, complete, max_outputs, on, number_of_streams
					
					String results = ((BroadcasterRtmpInCreationDriver)driver).testIMPL(tempParameters.get(0), tempParameters.get(1), tempParameters.get(2),
						tempParameters.get(3), tempParameters.get(4), tempParameters.get(5), tempParameters.get(6),tempParameters.get(7), tempParameters.get(8), 
						tempParameters.get(9),tempParameters.get(10), tempParameters.get(11), tempParameters.get(12),tempParameters.get(13), tempParameters.get(14),
						tempParameters.get(15),tempParameters.get(16), tempParameters.get(17), tempParameters.get(18),tempParameters.get(19), tempParameters.get(20),
						tempParameters.get(21));
					return results;
				}	
			});
		}
		
		// Execute concurrently all tasks.
		List<Future<String>> futures = executorService.invokeAll(callablesZtasks);
		
		String result;
		int numberOfAddedStreams = 0;
		
		for(Future<String> future : futures)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("added."))
			{
				numberOfAddedStreams ++;
			}
		}
		
		if (numberOfAddedStreams == counter)
			return "pass";
		else
			return "failed";
	}
	
	public String zexecute() throws InterruptedException, ExecutionException
	{
		ArrayList<Callable<String>> callablesZtasks = new ArrayList<Callable<String>>();
		int parameterSize = parameters.size();
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		for(int i = 0 ; i < counter; i++)
		{
			driver = new BroadcasterSinglePullInStreamCreationDriver();
			parameters.set(4,  "abc" + i);
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			Ztask ztask = new Ztask(driver, tempParameters);
			callablesZtasks.add(ztask);
		}
		
		List<Future<String>> futures = executorService.invokeAll(callablesZtasks);
		
		String result;
		int numberOfAddedStreams = 0;
		
		for(Future<String> future : futures)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("added."))
			{
				numberOfAddedStreams ++;
			}
		}
		
		if (numberOfAddedStreams == counter)
			return "pass";
		else
			return "failed";
	}
}
