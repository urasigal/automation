package com.zixi.zen.test;

import static com.zixi.globals.Macros.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcasterSetSshParametersDriver;
import com.zixi.drivers.tools.DriverReslut;
import com.zixi.testing.BaseTestZixiMainComponents;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ZenAttachBroadcasterToZenBroadcasterTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSetSshParametersDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"bxUserName", "bxUserPass", "bxLogin_ip", "bxUiport", "sshUserName", "sshHost", "ssh_port", 
	"clusterName", "zenLogin_ip", "zen_ssh_host", "zenUserName", "zenUserPass", "zenUiport", "broadcaserName", "testid"})
	@Test
	public void attachBroadcasterToZenBroadcaster(String bxUserName, String bxUserPass, String bxLogin_ip, String bxUiport, String sshUserName, 
	String sshHost, String ssh_port, String clusterName, String zenLogin_ip, String zen_ssh_host, 
	String zenUserName, String zenUserPass, String zenUiport, String broadcaserName, String testid)
	throws Exception {
		// Provide parameters to a TestLink.  
		buildTestParametersString(new String[] { "bxUserName", "bxUserPass", "bxLogin_ip", "bxUiport", "sshUserName", 
		"sshHost", "ssh_port", "clusterName", "zenLogin_ip", "zen_ssh_host", "broadcaserName", "testid"},  
		new String[] {bxUserName, bxUserPass, bxLogin_ip, bxUiport, sshUserName, sshHost, ssh_port, clusterName, zenLogin_ip, 
		zen_ssh_host, broadcaserName, testid});
		String addSshHost = ((BroadcasterSetSshParametersDriver) testDriver).setHostandSshPortBroadcaster
		(bxUserName, bxUserPass, bxLogin_ip, bxUiport, sshHost, ssh_port).getResult();
		String addKey = ((BroadcasterSetSshParametersDriver) testDriver).
		uploadSshKeyToBroadcasterZen(bxUserName, bxUserPass, bxLogin_ip, bxUiport).getResult();
		JSONObject json = new JSONObject(); 
		json.put("username", zenUserName).put("password", zenUserPass);
		String[] cokieValuesForLoggin = new ApiWorkir().zenLogginPost("http://" + zenLogin_ip + "/login" , zenUserName , zenUserPass, 
		zenUiport, zenLogin_ip, json.toString().getBytes());
		String broadcaster_cluster_id = new ApiWorkir().zenSendGet("http://" + zenLogin_ip + "/api/broadcaster_clusters", ZEN_CLUSTER_ID, 
		cokieValuesForLoggin, zenLogin_ip, zenUiport, clusterName);
		String rev_ssh_port = new ApiWorkir().zenSendGet("http://" + zenLogin_ip + "/api/broadcaster_clusters/" + broadcaster_cluster_id + 
		"/broadcasters", ZEN_GET_BX_REV_PORT,
		cokieValuesForLoggin, zenLogin_ip, zenUiport, broadcaserName);
		String addSshUserResult = ((BroadcasterSetSshParametersDriver) testDriver).setSshUserNameBroadcaster(bxUserName, bxUserPass, bxLogin_ip, 
		bxUiport, broadcaserName + "-" + clusterName, zen_ssh_host).getResult();  
		String setSshPort = ((BroadcasterSetSshParametersDriver) testDriver).setSshPortBroadcaster(bxUserName, bxUserPass, bxLogin_ip, bxUiport,
		rev_ssh_port, zen_ssh_host).getResult();
		Assert.assertEquals(driverReslut.getResult(), "Tunnel added." ); 
	}
}
