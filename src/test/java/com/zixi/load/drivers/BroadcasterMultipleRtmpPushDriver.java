package com.zixi.load.drivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import com.zixi.drivers.TestDriver;
import com.zixi.threads.ZthreadPool;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterMultipleRtmpPushDriver extends BroadcasterLoggableApiWorker implements TestDriver
{
	
//	 login_ipBX1,  login_ipBX2,  userNameBX1, userNameBX2,  userPasswordBX1,  userPasswordBX2,  uiportBX1, 
//	 uiportBX2,  typeBX1,  name,  stream,  matrixBX1,  url, url_alt,  rtmp_stream,  user,  bandwidth,  latency,  reconnect,  sendfi, disconnect_low_br, 
//	 static_latency,  dec_type,  dec_key,  password,  typeBX2,  id,  matrixBX2, max_outputs,  mcast_out,  time_shift, old,
//	 fast_connect,  kompression, enc_type,  enc_key,  rec_history,  rec_duration,  rtmp_url, rtmp_name,  rtmp_user
	 
	public String testIMPL(String... args) throws InterruptedException, ExecutionException
	{	
		// Pass parameters to arrayList in order to provide them to ZthreadPool.
		ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(args));
		
		ZthreadPool zthreadPool =  new ZthreadPool(10, parameters);
		return zthreadPool.executeRtmpPush();
	}
}
