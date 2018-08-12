package com.zixi.zen.test;

import static com.zixi.globals.Macros.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverSshConnectionDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;
import com.zixi.tools.ApiWorkir;

public class ZenAttachReceiverToZenREceiverTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ReceiverSshConnectionDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"receiverUserName", "receiverUserPass", "receiverLogin_ip", "receiverUiport", "sshHost", "ssh_port", "ssh_user", 
	"zenUserName", "zenUserPass","zenLogin_ip", "zenUiport", "testid"})
	@Test
	public void attachReceiverToREceiverZen(String receiverUserName,  String receiverUserPass, String receiverLogin_ip, 
	String receiverUiport, String sshHost, String ssh_port, String ssh_user, String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"receiverUserName", "receiverUserPass", "receiverLogin_ip", "receiverUiport", "sshHost", "ssh_port", 
		"ssh_user", "zenUserName", "zenUserPass","zenLogin_ip", "zenUiport", "testid"}, 
		new String[] {receiverUserName, receiverUserPass, receiverLogin_ip, receiverUiport, sshHost, ssh_port, ssh_user, zenUserName, zenUserPass, zenLogin_ip, zenUiport, testid});
		driverReslut = ((ReceiverSshConnectionDriver) testDriver).setSshUserAndSshHostReceiver(
						 receiverUserName,  receiverUserPass, receiverLogin_ip, receiverUiport, sshHost, ssh_port, ssh_user + "-receiver");
		driverReslut = ((ReceiverSshConnectionDriver) testDriver).uploadSshKeyToReceiverZen( receiverUserName, receiverUserPass, receiverLogin_ip, receiverUiport);
		
		JSONObject json = new JSONObject(); 
		json.put("username", zenUserName).put("password", zenUserPass);
		
		String[] cokieValuesForLoggin = new ApiWorkir().zenLogginPost("https://" + zenLogin_ip + "/login" , zenUserName , zenUserPass, 
				zenUiport, zenLogin_ip, json.toString().getBytes());
		
		String rev_ssh_port = new ApiWorkir().zenSendGet("https://" + zenLogin_ip + "/api/receivers", ZEN_GET_RECEIVER_REV_PORT,
				cokieValuesForLoggin, zenLogin_ip, zenUiport, ssh_user);
		driverReslut = ((ReceiverSshConnectionDriver) testDriver).setSshPortReceiver(receiverUserName, receiverUserPass, receiverLogin_ip, receiverUiport, rev_ssh_port); 
		
		Assert.assertEquals(driverReslut.getResult(), "Tunnel added."); 
	}
}