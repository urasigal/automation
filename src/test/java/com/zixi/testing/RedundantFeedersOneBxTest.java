package com.zixi.testing;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;
import com.zixi.ssh.*;
import com.zixi.threads.ExternalRunnerThread;
import com.zixi.tools.StreamStatisticAnalyzer;
import com.zixi.drivers.*;

public class RedundantFeedersOneBxTest extends BaseTest {
	private TestDriver inputStreamDetailsDriver;
	
	private TestDriver redundantFeederOneBxDriver;
	private SshJcraftClient sshJcraftClient;
	@BeforeClass
	public void testInit() {
		inputStreamDetailsDriver = new InputStreamDetailsDriver();
		redundantFeederOneBxDriver = new RedundantFeederOneBxDriver();
		sshJcraftClient = new SshJcraftClient();
	}

	@Parameters({ 
		"udp_port_server", // needed for UDP server, UDP server will be listen to this port. 
		
		"bx_stream_id",
		"middle_bx_login_ip", 
		"middle_bx_uiport", 
		"middle_bx_userName", 
		"middle_bx_userPass",
		
		"sshUser", 
		"sshPassword", 
		"sshPort", 
		"command",
		
		"testid"
		})
	@Test
	public void redundancyFxFxBx(String udp_port, String bx_stream_id, String middle_bx_login_ip, 
			String middle_bx_uiport, String middle_bx_userName, 
			String middle_bx_userPass, String sshUser, String sshPassword, String sshPort, 
			String command, String testid) throws InterruptedException, IOException, JSchException 
	{
		this.testid = testid;
		
		
		String sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfInputStream(bx_stream_id , middle_bx_login_ip, middle_bx_uiport, 
								middle_bx_userName, middle_bx_userPass);
		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
		((RedundantFeederOneBxDriver)redundantFeederOneBxDriver).testIMPL(Integer.parseInt(udp_port));
		
		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixifeeder start");
		
		ArrayList<Long> results = new ArrayList<Long>(); 
		
		for(int i = 0 ; i < 10; i++)
		{
			ExternalRunnerThread externalRunnerThread = new ExternalRunnerThread(((RedundantFeederOneBxDriver)redundantFeederOneBxDriver), udp_port);
			sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfInputStream(bx_stream_id , middle_bx_login_ip, middle_bx_uiport, 
					middle_bx_userName, middle_bx_userPass);
			externalRunnerThread.start();
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
			//((RedundantFeederOneBxDriver)redundantFeederOneBxDriver).testIMPL(Integer.parseInt(udp_port));
			externalRunnerThread.join();
			long result = externalRunnerThread.getResults();
			results.add(i, result);
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixifeeder start");
			Thread.currentThread().sleep(10000);
		}
		long statistics[] = StreamStatisticAnalyzer.getMaxMinAvg(results);
		System.out.println("Max is " + statistics[0] + "Min is " + statistics[1] + "Avg is " + statistics[2]);
	}
	
	@Parameters({ 
		"udp_port_server", // needed for UDP server, UDP server will be listen to this port. 
		
		"bx_stream_id",
		"middle_bx_login_ip", 
		"middle_bx_uiport", 
		"middle_bx_userName", 
		"middle_bx_userPass",
		
		"sshUser", 
		"sshPassword", 
		"sshPort", 
		"command",
		
		"testid"
		})
	@Test
	public void redundancyFxFxRx(String udp_port, String bx_stream_id, String middle_bx_login_ip, 
			String middle_bx_uiport, String middle_bx_userName, 
			String middle_bx_userPass, String sshUser, String sshPassword, String sshPort, 
			String command, String testid) throws InterruptedException, IOException, JSchException 
	{
		this.testid = testid;
		
		String sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfInputStream(bx_stream_id , middle_bx_login_ip, middle_bx_uiport, 
								middle_bx_userName, middle_bx_userPass);
		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
		((RedundantFeederOneBxDriver)redundantFeederOneBxDriver).testIMPL(Integer.parseInt(udp_port));
		
		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixifeeder start");
		
		ArrayList<Long> results = new ArrayList<Long>(); 
		
		for(int i = 0 ; i < 10; i++)
		{
			ExternalRunnerThread externalRunnerThread = new ExternalRunnerThread(((RedundantFeederOneBxDriver)redundantFeederOneBxDriver), udp_port);
			sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfInputStream(bx_stream_id , middle_bx_login_ip, middle_bx_uiport, 
					middle_bx_userName, middle_bx_userPass);
			externalRunnerThread.start();
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
			//((RedundantFeederOneBxDriver)redundantFeederOneBxDriver).testIMPL(Integer.parseInt(udp_port));
			externalRunnerThread.join();
			long result = externalRunnerThread.getResults();
			results.add(i, result);
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixifeeder start");
			Thread.currentThread().sleep(10000);
		}
		long statistics[] = StreamStatisticAnalyzer.getMaxMinAvg(results);
		System.out.println("Max is " + statistics[0] + " Min is " + statistics[1] + " Avg is " + statistics[2]);
	}
}
