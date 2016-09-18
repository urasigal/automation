package com.zixi.load.drivers;

import static com.zixi.globals.Macros.PUSHOUTMODE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import com.zixi.drivers.TestDriver;
import com.zixi.threads.ZthreadPool;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.JsonParser;

public class FeederMultipleDeletionDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	public String testIMPL(String... args) throws Exception
	{	
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + args[2] + ":" + args[3] + "/login.htm", args[0], args[1], args[2], args[3]);

		//"userName_feeder", "userPass_feeder", "login_ip_feeder", "uiport"
		
		// Get feeder's output ids.
		String streamsJson =  apiworker.sendGet("http://"+ args[2] +":"+ args[3] + "/outs", "", 77, responseCookieContainer, args[2], this, args[3]);
		ArrayList<String> ids = JsonParser.feederOutsIds(streamsJson);
		
		// Pass parameters to arrayList in order to provide them to ZthreadPool.
		ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(args));
		
		ZthreadPool zthreadPool =  new ZthreadPool(1, parameters);
		return zthreadPool.zexecuteFeederDeletion(ids);
	}

}
