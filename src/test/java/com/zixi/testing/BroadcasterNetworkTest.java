package com.zixi.testing;

import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.NetworkingDriver;
import com.zixi.tools.JsonParser;

public class BroadcasterNetworkTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new NetworkingDriver();
	}
	
	@Parameters({"sshuser", "sshpass", "sshaddress", "sshport", "command", "login_ip", "uiport", "userName", "userPass","testid" })
	@Test
	public void compareIpsTest(String sshuser, String sshpass, String sshaddress, String sshport,  String command, String login_ip, String uiport, 
	String userName, String userPass, String testid) throws Exception {
		
		 productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "sshuser", "sshpass", "sshaddress", "sshport", "command", "login_ip", "uiport",
		"userName", "userPass","testid" }, new String[] { sshuser, sshpass, sshaddress, sshport, command, login_ip, uiport, userName, userPass,testid});
		
		String ips[] = ((NetworkingDriver)testDriver).getServerInterfacesAddresses(sshuser, sshpass, sshaddress, sshport, command);
		List<String> ipz = JsonParser.retriveIpAddresses(((NetworkingDriver)testDriver).getIpsFromApi(login_ip, uiport, userName, userPass));
		for (String ip : ipz) {
			System.out.println("Api output is " + ip);
		}
	}
}
