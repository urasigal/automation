package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterInputStatisticSingleStreamDriver;
import com.zixi.drivers.BroadcasterSinglePullInStreamCreationDriver;
import com.zixi.drivers.TestDriver;
import com.zixi.entities.StreamEntity;

public class BroadcasterInputStatisticSingleStreamTest {
	
	private TestDriver testDriver;
	@BeforeClass
	public void testInit() {
		
		// Load the page in the browser
		testDriver = new BroadcasterInputStatisticSingleStreamDriver();
	}

	@Parameters({ "userName","userPass","Host","loin_ip","uiport","id", "testduration"})
	@Test
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String Host, String loin_ip, String uiport,String id, String testduration) throws InterruptedException 
	{
		Assert.assertNotNull(((BroadcasterInputStatisticSingleStreamDriver) testDriver).testStatistic(userName, userPass, Host, loin_ip, uiport, id, testduration));
	}
}
