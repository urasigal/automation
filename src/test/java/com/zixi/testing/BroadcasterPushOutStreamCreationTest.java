package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.TestDriver;

public class BroadcasterPushOutStreamCreationTest {
	private TestDriver testDriver;
	
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterPushOutStreamCreationDriver();
	}

	@Parameters({ "userName","userPass", "login_ip", "host", "latency", "fec_force" ,
		          "session", "fec_adaptive", "nic", "fec_block", "type",
		          "snames", "fec_aware", "fec_overhead", "stream", "port", "uiport", "alias","id"})
	
	@Test
	public void broadcasterPullInCreation(String userName,String userPass, String login_ip, String host, String latency, String fec_force ,
			String session, String fec_adaptive, String nic, String fec_block, String type,
			String snames, String fec_aware, String fec_overhead, String stream, String port, String uiport, String alias,String id) throws InterruptedException 
	{
		 Assert.assertEquals(((BroadcasterPushOutStreamCreationDriver) testDriver).testIMPL(  userName, userPass,  login_ip,  host,  latency,  fec_force ,
					 session,  fec_adaptive,  nic,  fec_block,  type,
					 snames,  fec_aware,  fec_overhead,  stream,  port,  uiport,  alias, id),"Output " + id +" added.");
	}

}
