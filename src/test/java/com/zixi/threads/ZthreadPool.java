package com.zixi.threads;

import static com.zixi.globals.Macros.UDPMODE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.zixi.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.BroadcasterPushInStreamCreationDriver;
import com.zixi.drivers.BroadcasterPushOutStreamCreationDriver;
import com.zixi.drivers.BroadcasterRtmpInCreationDriver;
import com.zixi.drivers.BroadcasterRtmpPushInputStreamDriver;
import com.zixi.drivers.BroadcasterRtmpPushOutputCreationDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.BroadcasterSingleUdpInCreationDriver;
import com.zixi.drivers.BroadcasterUdpOutputCreationDriver;
import com.zixi.drivers.FeederOutputDeletionDriver;
import com.zixi.drivers.FeederOutputPushToBxDriver;
import com.zixi.drivers.StreamsDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.testing.BroadcaserSingleOutputStreamDeletionTest;
import com.zixi.testing.BroadcasterPushInStreamCreationDecriptionTest;
import com.zixi.testing.FeederBondedOutputDeletioinTest;
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
	
	
	public String executeDeleteAll() throws Exception 
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
				public String call() throws Exception
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
		
		ArrayList<String> splittedResults = new ArrayList<>();
		
		for(Future<String> response : futuresOutPutStreamDeletion)
		{
			splittedResults.add(response.get().split(" ")[0]);
		}
		
		if(splittedResults.size() == outputIds.size())
		{
			return "good";
		}
		return "bad";
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
				public String call() throws Exception
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
				public String call() throws Exception
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
		List<Future<String>> futuresInputStreamCreation  = executorService.invokeAll(callablesZtasks2);
		List<Future<String>> futuresOutPutStreamCreation = executorService.invokeAll(callablesZtasks1);
		
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
			return numberOfAddedInputPutStreams + "";
		else
			return numberOfAddedInputPutStreams + "failed";
	}
	
	public String zexecuteUdp() throws InterruptedException, ExecutionException
	{
		// This is a containers for the Callable tasks.
		ArrayList<Callable<String>> callablesZtasks1 = new ArrayList<Callable<String>>();
		ArrayList<Callable<String>> callablesZtasks2 = new ArrayList<Callable<String>>();
		
		// number of test parameters.
		int parameterSize = parameters.size();
		
		// Gets a number of desired tasks.
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		
		for(int i = 0 ; i < counter; i++)
		{
			TestDriver driver1 = new BroadcasterSingleUdpInCreationDriver();
			TestDriver driver2 = new BroadcasterUdpOutputCreationDriver();
					
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			
			tempParameters.set(7,  parameters.get(7) + i);
			tempParameters.set(29,  parameters.get(29) + i); 
			tempParameters.set(31,  parameters.get(31) + i); 
			
			tempParameters.set(6,  ((Integer.parseInt((parameters.get(6)))) + i) + "");
			tempParameters.set(27,  ((Integer.parseInt((parameters.get(27)))) + i) + ""); 
			
			// Add and create a callable tasks for PUSH input stream creation. 
			callablesZtasks1.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((BroadcasterSingleUdpInCreationDriver)driver1).testIMPL(
					tempParameters.get(0), // 
					tempParameters.get(2), // 
					tempParameters.get(4), // 
					tempParameters.get(6), // 
					tempParameters.get(7), // 
					tempParameters.get(8), // 
					tempParameters.get(9), // 
					tempParameters.get(10),// 
					tempParameters.get(11),// 
					tempParameters.get(12),// 
					tempParameters.get(13),//   
					tempParameters.get(14),// 
					tempParameters.get(15),// 
					tempParameters.get(16),//  
					tempParameters.get(17),// 
					tempParameters.get(18),// 
					tempParameters.get(19),// 
					tempParameters.get(20),//  
					tempParameters.get(21),
					tempParameters.get(22),
					tempParameters.get(23),
					tempParameters.get(24),
					tempParameters.get(25),
					tempParameters.get(26));//
					return results;
				}	
			});
			
			// Add and create a callable tasks for RTMP push input stream creation. 
			callablesZtasks2.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((BroadcasterUdpOutputCreationDriver)driver2).testIMPL(
					tempParameters.get(1),
					tempParameters.get(3),
					tempParameters.get(5),
					tempParameters.get(27),
					tempParameters.get(28),
					tempParameters.get(29),
					tempParameters.get(30),
					tempParameters.get(31),
					tempParameters.get(32),
				    tempParameters.get(33),
					tempParameters.get(34),
					tempParameters.get(35),
					tempParameters.get(36), 
					tempParameters.get(37),
					tempParameters.get(38), 
					tempParameters.get(39),
					tempParameters.get(40),
					tempParameters.get(41),
					tempParameters.get(42),
					tempParameters.get(43),
					tempParameters.get(44),
					tempParameters.get(45),
					tempParameters.get(46),
					tempParameters.get(47),
					tempParameters.get(48));
					return results;
				}	
			});
		} 
		
		// Execute concurrently all tasks.
		List<Future<String>> futuresInputStreamCreation   = executorService.invokeAll(callablesZtasks1);
		List<Future<String>> futuresOutPutStreamCreation  = executorService.invokeAll(callablesZtasks2);
		
		String result;
		int numberOfAddedOutPutStreams = 0;
		int numberOfAddedInputPutStreams = 0;
		
		for(Future<String> future : futuresOutPutStreamCreation)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("good"))
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
	
	// Relates to the PUSH load testing.
	public String zexecutePush() throws InterruptedException, ExecutionException
	{
		// This is a container for the Callable tasks.
		ArrayList<Callable<String>> callablesZtasks1 = new ArrayList<Callable<String>>();
		ArrayList<Callable<String>> callablesZtasks2 = new ArrayList<Callable<String>>();
		
		int parameterSize = parameters.size();
		
		// Gets a number of desired tasks.
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		for(int i = 0 ; i < counter; i++)
		{
			TestDriver driver1 = new BroadcasterPushInStreamCreationDriver();
			TestDriver driver2 = new BroadcasterPushOutStreamCreationDriver();
					
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			
			tempParameters.set(16,  parameters.get(16) + i);
			tempParameters.set(36,  parameters.get(36) + i); 
			tempParameters.set(37,  parameters.get(37) + i); 
			
			// Add and create a callable tasks for PUSH input stream creation. 
			callablesZtasks1.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((BroadcasterPushInStreamCreationDriver)driver1).testIMPL(
					tempParameters.get(0), // userName
					tempParameters.get(2), // userPass
					tempParameters.get(4), // login_ip
					tempParameters.get(6), // latency
					tempParameters.get(7), // time_shift
					tempParameters.get(8), // force_p2p
					tempParameters.get(9), // mcast_ip
					tempParameters.get(10),// mcast_force
					tempParameters.get(11),// mcast_port
					tempParameters.get(12),// type
					tempParameters.get(13),// uiport  
					tempParameters.get(14),// analyze
					tempParameters.get(15),// mcast_ttl
					tempParameters.get(16),// id 
					tempParameters.get(17),// mcast_out
					tempParameters.get(18),// complete
					tempParameters.get(19),// max_outputs
					tempParameters.get(20),// on 
					tempParameters.get(21));//password
					return results;
				}	
			});
			
			// Add and create a callable tasks for RTMP push input stream creation. 
			callablesZtasks2.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((BroadcasterPushOutStreamCreationDriver)driver2).testIMPL(
					tempParameters.get(1),
					tempParameters.get(3),
					tempParameters.get(5),
					tempParameters.get(22),
					tempParameters.get(23),
					tempParameters.get(24),
					tempParameters.get(25),
					tempParameters.get(26),
					tempParameters.get(27),
				    tempParameters.get(28),
					tempParameters.get(29),
					tempParameters.get(30),
					tempParameters.get(31), 
					tempParameters.get(32),
					tempParameters.get(33), 
					tempParameters.get(34),
					tempParameters.get(35),
					tempParameters.get(36),
					tempParameters.get(37));
					return results;
				}	
			});
		} // end for
		// Execute concurrently all tasks.
		List<Future<String>> futuresInputStreamCreation   = executorService.invokeAll(callablesZtasks1);
		List<Future<String>> futuresOutPutStreamCreation  = executorService.invokeAll(callablesZtasks2);
		
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
				public String call() throws Exception
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
	
	
	
	// Relates to the Bonded load testing.
	public String zexecuteBondedFeeder2Bx() throws InterruptedException, ExecutionException
	{
		// This is a container for the Callable tasks.
		ArrayList<Callable<String>> callablesZtasks1 = new ArrayList<Callable<String>>();
		ArrayList<Callable<String>> callablesZtasks2 = new ArrayList<Callable<String>>();
		
		int parameterSize = parameters.size();
		
		// Gets a number of desired tasks.
		int counter = Integer.parseInt( parameters.get(parameterSize -1) );
		
		for(int i = 0 ; i < counter; i++)
		{
			TestDriver driver1 = new FeederOutputPushToBxDriver();
			TestDriver driver2 = new BroadcasterPushInStreamCreationDriver();
					
			ArrayList<String> tempParameters = (ArrayList<String>)parameters.clone();
			
			tempParameters.set(3,  parameters.get(3) + i);
			tempParameters.set(10,  parameters.get(10) + i); 
			tempParameters.set(63,  parameters.get(63) + i); 
			
			// Add and create a callable tasks for PUSH input stream creation. 
			callablesZtasks1.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((FeederOutputPushToBxDriver)driver1).testIMPL(
					tempParameters.get(0), 
					tempParameters.get(1), 
					tempParameters.get(2),
					tempParameters.get(3), 
					tempParameters.get(4), 
					tempParameters.get(5), 
					tempParameters.get(6), 
					tempParameters.get(7),
					tempParameters.get(8),
					tempParameters.get(9),
					tempParameters.get(10),
					tempParameters.get(11),
					tempParameters.get(12),
					tempParameters.get(13),
					tempParameters.get(14),
					tempParameters.get(15),
					tempParameters.get(16),
					tempParameters.get(17),
					tempParameters.get(18),
					tempParameters.get(19),
					tempParameters.get(20),
					tempParameters.get(21),
					tempParameters.get(22),
					tempParameters.get(23),
					tempParameters.get(24),
					tempParameters.get(25),
					tempParameters.get(26),
					tempParameters.get(27),
					tempParameters.get(28),
					tempParameters.get(29),
					tempParameters.get(30),
					tempParameters.get(31),
					tempParameters.get(32),
					tempParameters.get(33),
					tempParameters.get(34),
					tempParameters.get(35),
					tempParameters.get(36),
					tempParameters.get(37),
					tempParameters.get(38),
					tempParameters.get(39),
					tempParameters.get(40),
					tempParameters.get(41),
					tempParameters.get(42),
					tempParameters.get(43),
					tempParameters.get(44),
					tempParameters.get(45),
					tempParameters.get(46),
					tempParameters.get(47),
					tempParameters.get(48),
					tempParameters.get(49));
					return results;
				}	
			});
			
			// Add and create a callable tasks for RTMP push input stream creation. 
			callablesZtasks2.add(new Callable<String>()
			{
				public String call() throws Exception
				{
					String results = ((BroadcasterPushInStreamCreationDriver)driver2).testIMPL(
					tempParameters.get(50),
					tempParameters.get(51),
					tempParameters.get(52),
					tempParameters.get(53),
					tempParameters.get(54),
					tempParameters.get(55),
					tempParameters.get(56),
					tempParameters.get(57),
				    tempParameters.get(58),
					tempParameters.get(59),
					tempParameters.get(60),
					tempParameters.get(61), 
					tempParameters.get(62),
					tempParameters.get(63), 
					tempParameters.get(64),
					tempParameters.get(65),
					tempParameters.get(66),
					tempParameters.get(67),
					tempParameters.get(68),
					tempParameters.get(69),
					tempParameters.get(70));
					return results;
				}	
			});
		} // end for
		// Execute concurrently all tasks.
		List<Future<String>> futuresInputStreamCreation   = executorService.invokeAll(callablesZtasks1);
		List<Future<String>> futuresOutPutStreamCreation  = executorService.invokeAll(callablesZtasks2);
		
		String result;
		int numberOfAddedBondedStreams = 0;
		int numberOfAddedInputPutStreams = 0;
		
		for(Future<String> future : futuresOutPutStreamCreation)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("added."))
			{
				numberOfAddedBondedStreams ++;
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
		
		if (numberOfAddedInputPutStreams == counter && numberOfAddedBondedStreams == counter)
			return "pass";
		else
			return "failed";
	}
	
	
	public String zexecuteFeederDeletion(ArrayList<String> ids) throws InterruptedException, ExecutionException 
	{
		ArrayList<Callable<String>> callablesZtasks = new ArrayList<Callable<String>>();
		int parameterSize = parameters.size();
		
		TestDriver driver = new FeederOutputDeletionDriver();
		
		int numberOfOutputs = ids.size();
		
		for(int i = 0 ; i < numberOfOutputs; i++)
		{
			String tmpId = ids.get(i);
			callablesZtasks.add(new Callable<String>(){
				
				public String call() throws Exception
				{
					// String userName, String userPass, String login_ip, String uiport, String id
					
					// userName_feeder, userPass_feeder, login_ip_feeder, uiport, mip, port, ip, prog, chan, type
					String results = ((FeederOutputDeletionDriver)driver).testIMPL( parameters.get(0), parameters.get(1), parameters.get(2), 
					parameters.get(3), parameters.get(4), parameters.get(5), parameters.get(6), parameters.get(7), parameters.get(8), parameters.get(9), tmpId);		
					
					return results; 
				}	
			});
		}
		
		List<Future<String>> futures = executorService.invokeAll(callablesZtasks);
		
		String result;
		int numberOfAddedStreams = 0;
		
		for(Future<String> future : futures)
		{
			result = future.get();
			System.out.println("Result is " +  result);
			String[] res = result.split(" ");
			if(res[res.length - 1].equals("deleted."))
			{
				numberOfAddedStreams ++;
			}
		}
		
		if (numberOfAddedStreams == numberOfOutputs)
			return "pass";
		else
			return "failed";
	}
}
