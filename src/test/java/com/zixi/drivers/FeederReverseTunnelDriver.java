package com.zixi.drivers;

import static com.zixi.globals.Macros.FEEDER_SSH_SERVER_STATUS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.zixi.ssh.SshJcraftClient;
import com.zixi.tools.BroadcasterLoggableApiWorker;


public class FeederReverseTunnelDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	private SshJcraftClient sshJcraftClient;
	 
	public String testIMPL(String userName, String sshPass, String login_ip, String ssh_port, String ssh_user, String reverse_port) throws IOException, InterruptedException, JSchException  
	{
		sshJcraftClient = new SshJcraftClient();
		Thread.sleep(10000);
		
		String test = sshJcraftClient.callCommand(userName, sshPass, login_ip, ssh_port, "lsof -nPi | grep " +  reverse_port);
		
		return test.split(" ")[0];
	}

}
