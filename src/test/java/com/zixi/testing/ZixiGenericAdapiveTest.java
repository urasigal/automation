package com.zixi.testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.BroadcasterCreateAddaptiveGroupDriver;

public class ZixiGenericAdapiveTest extends BaseTest {

	@Parameters({ "driver", "userName", "userPass", "login_ip", "uiport",
			"name", "record", "zixi", "hls", "hds", "mpd", "mmt",
			"compress_zixi", "multicast", "streams", "bitrates", "max_time",
			"testid" })
	@BeforeClass
	public void testInit(String driver, String userName, String userPass,
			String login_ip, String uiport, String name, String record,
			String zixi, String hls, String hds, String mpd, String mmt,
			String compress_zixi, String multicast, String streams,
			String bitrates, String max_time, String testid)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException {
		this.testid = testid;
		c = Class.forName(driver);
		driverObj = c.newInstance();
		m = c.getMethod("testIMPL", new Class[] { String.class , String.class , String.class ,
				String.class , String.class , String.class , String.class , String.class ,
				String.class , String.class , String.class , String.class ,
				String.class , String.class , String.class , String.class });

		params = new Object[] { userName,  userPass,  login_ip,
				 uiport,  name, record,  zixi,  hls,
				 hds,  mpd, mmt, compress_zixi,
				 multicast, streams, bitrates, max_time};
	}

	@Test
	public void adaptiveToFfmpegTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		if (m != null) {
			Assert.assertEquals(m.invoke(driverObj, params), "{\"success\":1}");
		}
	}
}