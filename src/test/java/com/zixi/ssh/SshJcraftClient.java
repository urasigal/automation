package com.zixi.ssh;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshJcraftClient {
	
	private JSch jsch;
	private Session session;
	private Channel channel;
	private int statusFlag = 0;
	
	public SshJcraftClient()
	{
		jsch = new JSch();
	}
	
	public void setSession(String userName, String password, String host, int port)
	{
		if (jsch != null)
		{
			try {
				session=jsch.getSession(userName, host, port);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(password);
				session.connect();
				statusFlag = 1;
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void openChannel()
	{
		if (statusFlag == 1)
		{
			 try {
				 channel=session.openChannel("exec");
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void performCommand(String command) throws InterruptedException, IOException, JSchException
	{
		((ChannelExec)channel).setCommand(command);
		 InputStream in=channel.getInputStream();
		((ChannelExec)channel).setErrStream(System.err);
		byte[] tmp=new byte[1024];
		channel.connect();
		
		// Whait
		while(true)
		{
	        while(in.available()>0)
	        {
	          int i=in.read(tmp, 0, 1024);
	          if(i<0)break;
	          System.out.print(new String(tmp, 0, i)); 
	        }
	        if(channel.isClosed())
	        {
	          if(in.available()>0) continue; 
	          System.out.println("exit-status: "+channel.getExitStatus());
	          break;
	        }
	        //try{Thread.sleep(1000);}catch(Exception ee){}
	     }
		// Close a connection both session and channel
		channel.disconnect();
		session.disconnect();
	}
	
	public String callCommand(String command) throws InterruptedException, IOException, JSchException
	{
		((ChannelExec)channel).setCommand(command);
		 InputStream in=channel.getInputStream();
		((ChannelExec)channel).setErrStream(System.err);
		byte[] tmp=new byte[1024];
		channel.connect();
		StringBuffer stringBuf = new StringBuffer();
		
		// Whait
		while(true)
		{
	        while(in.available()>0)
	        {
	          int i=in.read(tmp, 0, 1024);
	          if(i<0)break;
	          stringBuf.append(new String(tmp, 0, i)); 
	          //System.out.print(stringBuf); 
	        }
	        if(channel.isClosed())
	        {
	          if(in.available()>0) continue; 
	          //System.out.println("exit-status: "+channel.getExitStatus());
	          break;
	        }
	        //try{Thread.sleep(1000);}catch(Exception ee){}
	     }
		// Close a connection both session and channel
		channel.disconnect();
		session.disconnect();
		return stringBuf.toString();
	}
	
	public void performCommand(String sshUser, String sshPassword, String sshLoginIp, String sshPort, String command) throws InterruptedException, IOException, JSchException
	{
		
		setSession(sshUser, sshPassword, sshLoginIp, Integer.parseInt(sshPort));
		openChannel();
		performCommand(command);
	}
	
	public String callCommand(String sshUser, String sshPassword, String sshLoginIp, String sshPort, String command) throws InterruptedException, IOException, JSchException
	{
		setSession(sshUser, sshPassword, sshLoginIp, Integer.parseInt(sshPort));
		openChannel();
		return callCommand(command);
	}
	
	public static void main(String[] args) throws InterruptedException, IOException, JSchException
	{
		SshJcraftClient sshJcraftClient = new SshJcraftClient();
		sshJcraftClient.setSession("root", "zixiroot1234", "10.7.0.63", 22);
		sshJcraftClient.openChannel();
		System.out.println(sshJcraftClient.callCommand("pidof zixi_broadcaster"));
	}
}
