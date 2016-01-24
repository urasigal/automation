package com.zixi.drivers;

import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.zixi.ssh.SshJcraftClient;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class NetworkingDriver extends BroadcasterLoggableApiWorker
implements TestDriver {
	
	private ApiWorkir apiNetworkGetter = new ApiWorkir();
	private SshJcraftClient sshJcraftClient = new SshJcraftClient();
	
	public String[] getServerInterfacesAddresses(String sshUser, String sshPassword, String sshLoginIp, String sshPort, String command) throws InterruptedException, IOException, JSchException
	{
		return sshJcraftClient.callCommand( sshUser,  sshPassword,  sshLoginIp,  sshPort,  command).split(" ");
	}
	
	
	public String  getIpsFromApi(String login_ip, String uiport, String userName, String userPass)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport 
				+ "/login.htm", userName , userPass, login_ip, uiport);
		
		return apiNetworkGetter.sendGet("http://" + login_ip + ":" + uiport + "/network_data.json" , "", 77 , 
				responseCookieContainer, login_ip, this, uiport);
	}
	
	
	public static void main(String[] args) throws InterruptedException, IOException, JSchException
	{
		NetworkingDriver nd = new NetworkingDriver();
		String ips[] = nd.getServerInterfacesAddresses( "root",  "zixiroot1234", "10.7.0.62 ", "22", "hostname -I");
		for(int i = 0 ; i < ips.length - 1 ; i ++ )
		{
			System.out.println("IP is " + ips[i]);
		}
	}
}
