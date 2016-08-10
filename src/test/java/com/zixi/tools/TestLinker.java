package com.zixi.tools;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import com.zixi.drivers.*;
import com.zixi.testing.BaseTest;

public class TestLinker extends BaseTest{
private TestDriver testDriver;
	
@BeforeClass
public void testInit() { 
	testDriver = new TestLinkerDriver();
}

	@Parameters({"userName", "userPass", "login_ip", "uiport"})
	@Test
	public void setToBlockedStatusTestLinkedTests(String userName, String userPass, String login_ip, String uiport) throws Exception 
	{
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		Assert.assertEquals(((TestLinkerDriver) testDriver).testIMPL(version),
				"success");
	}
}
