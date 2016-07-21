package com.zixi.load.drivers;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.testng.internal.junit.ArrayAsserts;

import static com.zixi.globals.Macros.GENERALMODE;

import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.threads.ZthreadPool;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterMultipePullCreationDriver extends BroadcasterLoggableApiWorker implements TestDriver
{
	public String testIMPL(String userName, String userPass, String Host,
			String loin_ip, String id, String source, String uiport,
			String pull_port, String latency, String fec_latency,
			String fec_overhead, String mcast_force, String time_shift,
			String nic, String max_outputs, String type, String password,
			String mcast_port, String complete, String mcast_ip,
			String fec_adaptive, String mcast_ttl, String on, String func,
			String fec_force, String mcast_out, String propertiesFile, String number_of_streams) throws InterruptedException, ExecutionException
	{	
		TestDriver driver = new BroadcasterSinglePullInStreamCreationDriver();
		ArrayList<String> parameters = new ArrayList<>();
		parameters.add(userName);
		parameters.add(userPass);
		parameters.add(Host);
		parameters.add(loin_ip);
		parameters.add(id);
		parameters.add(source);
		parameters.add(uiport);
		parameters.add(pull_port);
		parameters.add(latency);
		parameters.add(fec_latency);
		parameters.add(fec_overhead);
		parameters.add(mcast_force);
		parameters.add(time_shift);
		parameters.add(nic);
		parameters.add(max_outputs);
		parameters.add(type);
		parameters.add(password);
		parameters.add(mcast_port);
		parameters.add(complete);
		parameters.add(mcast_ip);
		parameters.add(fec_adaptive);
		parameters.add(mcast_ttl);
		parameters.add(on);
		parameters.add(func);
		parameters.add(fec_force);
		parameters.add(mcast_out);
		parameters.add(propertiesFile);
		parameters.add(number_of_streams);
		ZthreadPool zthreadPool =  new ZthreadPool(5, parameters, driver);
		zthreadPool.zexecute();
		return number_of_streams;
	}
}
