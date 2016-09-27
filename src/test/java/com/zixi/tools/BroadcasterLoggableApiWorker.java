package com.zixi.tools;

import java.io.IOException;

import com.jcraft.jsch.JSchException;
import com.zixi.entities.TestParameters;
import com.zixi.ssh.SshJcraftClient;


public class BroadcasterLoggableApiWorker {
	
	protected ApiWorkir 					apiworker 						= new ApiWorkir();
	
	protected String 						responseCookieContainer[] 		= new String[2];
	
	protected TestParameters 				testParameters;
	
	protected BroadcasterInitialSecuredLogin broadcasterInitialSecuredLogin = new BroadcasterInitialSecuredLogin();
	
	private static SshJcraftClient 			 sshJcraftClient;
	
	protected StringBuffer 					 testFlowDescriptor;
	
	// Default constructor.
	public BroadcasterLoggableApiWorker() {}
	
	public BroadcasterLoggableApiWorker(StringBuffer testFlowDescriptor) {
		
		this.testFlowDescriptor = testFlowDescriptor;
	}

	public static String getPid(String sshUser, String sshPassword, String sshLoginIp, String sshPort, String command)
	{
		sshJcraftClient = new SshJcraftClient();
		try 
		{
			return sshJcraftClient.callCommand( sshUser,  sshPassword,  sshLoginIp,  sshPort,  command);
			// multiple exception catching >= SE7.   
		} catch (InterruptedException |  IOException | JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "no pid";
	}
}
