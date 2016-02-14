package com.zixi.drivers;

import static com.zixi.globals.Macros.UDPMODE;

import com.zixi.threads.DataGramZixiThread;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class RedundantFeederOneBxDriver  extends BroadcasterLoggableApiWorker
implements TestDriver{

	public long testIMPL(int udp_port)
	{
		DataGramZixiThread udpServer;
		udpServer = new DataGramZixiThread(udp_port);
		udpServer.start();
		try 
		{
			Thread.currentThread().sleep(40000);
			udpServer.setNotStop(false); 
			udpServer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return udpServer.getResult();
	}
	
	public static void main(String[] args)
	{
		RedundantFeederOneBxDriver driver = new RedundantFeederOneBxDriver();
		driver.testIMPL(9999);
	}
}
