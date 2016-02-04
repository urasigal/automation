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
		long statistics[] = StreamStatisticAnalyzer.getMaxMinAvgLong(results);
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
		long statistics[] = StreamStatisticAnalyzer.getMaxMinAvgLong(results);
		System.out.println("Max is " + statistics[0] + " Min is " + statistics[1] + " Avg is " + statistics[2]);
	}
	

	// Scenario: one BX (max outputs one), 2 receivers trying to pull the stream from bx, only one will succeed at a time.
	@Parameters({ 
		"udp_port_server",    // needed for UDP server, UDP server will be listen to this port. 
		
		"bx_stream_id",       // source stream id. bx side.
		"middle_bx_login_ip", // bx login ip address
		"middle_bx_uiport",   // Broadcaster UI port.
		"middle_bx_userName", // Broadcaster login user name (admin)
		"middle_bx_userPass", // Broadcaster login user password (1234)
		
		"sshUser",  		  // SSH user name have to be same for a two receivers servers.
		"sshPassword", 		 // SSH user password have to be same for two receivers servers.
		"sshPort", 			// SSH server listening port same for a both receivers servers.	
		"command",		   // SSH command to be executed.
		
		"testid"
		})
	@Test
	public void redundancyBxRxRx(String udp_port, String bx_stream_id, String middle_bx_login_ip, 
			String middle_bx_uiport, String middle_bx_userName, 
			String middle_bx_userPass, String sshUser, String sshPassword, String sshPort, 
			String command, String testid) throws InterruptedException, IOException, JSchException 
	{
		this.testid = testid; // This field is used in a testlink integration.
		String sshLoginIp;
		// Get destination's IP address of an active stream.
//		String sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfOutputStream(bx_stream_id , middle_bx_login_ip, 
//								middle_bx_uiport, middle_bx_userName, middle_bx_userPass);
//		
//		// Stop active's destination server.
//		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
//		
//		// Start UDP server to listen to an incoming UDP traffic.
//		((RedundantFeederOneBxDriver)redundantFeederOneBxDriver).testIMPL(Integer.parseInt(udp_port));
//		
//		// Start the previously stopped destination server.
//		sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixireceiver start");
//		
		ArrayList<Long> results = new ArrayList<Long>(); 
		
		for(int i = 0 ; i < 10; i++)
		{
			// Implements Runnable interface, will run a UDP server which 
			ExternalRunnerThread externalRunnerThread = new ExternalRunnerThread(((RedundantFeederOneBxDriver)redundantFeederOneBxDriver), udp_port);
			
			// Get destination IP address of the live output 
			sshLoginIp = ((InputStreamDetailsDriver)inputStreamDetailsDriver).findSourceIpOfOutputStream(bx_stream_id , middle_bx_login_ip, middle_bx_uiport, 
					middle_bx_userName, middle_bx_userPass);
			
			externalRunnerThread.start();
			
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, command);
			
			// Suspend the main thread (current) till the  UDP server will finish -- it waits at least 40 seconds or till the UDP packet will be received 
			// on a wire on an UDP server side. 
			externalRunnerThread.join();
			long result = externalRunnerThread.getResults();
			results.add(i, result);
			sshJcraftClient.performCommand(sshUser, sshPassword, sshLoginIp, sshPort, "service zixireceiver start");
			Thread.currentThread().sleep(10000);
		}
		long statistics[] = StreamStatisticAnalyzer.getMaxMinAvgLong(results);
		System.out.println("Max is " + statistics[0] + "Min is " + statistics[1] + "Avg is " + statistics[2]);
	}
	
	
	
	// Scenario: one BX, 1 receiver pull the stream from bx, then kill BX then stream from file backup.
		@Parameters({ 
			"udp_port_server",    // needed for UDP server, UDP server will be listen to this port. 
			
			"bx_stream_id",       // source stream id. bx side.
			"middle_bx_login_ip", // bx login ip address
			"middle_bx_uiport",   // Broadcaster UI port.
			"middle_bx_userName", // Broadcaster login user name (admin)
			"middle_bx_userPass", // Broadcaster login user password (1234)
			
			"sshUser",  		  // SSH user name have to be same for a two receivers servers.
			"sshPassword", 		 // SSH user password have to be same for two receivers servers.
			"sshPort", 			// SSH server listening port same for a both receivers servers.	
			"command",		   // SSH command to be executed.
			
			"receiver_ip",
			"recever_uiport",
			"receiver_user_name",
			"receiver_password",
			"receiver_main_stream_name",
			"receiver_back_up_name",
			
			"testid"
			})
		@Test
		public void redundancyReceiverBackUp(String udp_port, String bx_stream_id, String middle_bx_login_ip, 
				String middle_bx_uiport, String middle_bx_userName, 
				String middle_bx_userPass, String sshUser, String sshPassword, String sshPort, 
				String command, 
				String receiver_ip,
			 	String recever_uiport,
				String receiver_user_name,
				String receiver_password,
				String receiver_main_stream_name,
				String receiver_back_up_name,  
				String testid) throws Exception 
		{
			this.testid = testid; // This field is used to provide a testlink integration.
			String sshLoginIp;
			
			// This class is used for getting an input stream meta data.
			StreamsDriver inputStreams = new StreamsDriver();
			
			
			// Get a main steam id by its name.
			String mainId   = inputStreams.getOutputStreamIdByName(receiver_main_stream_name, receiver_ip, recever_uiport, receiver_user_name, receiver_password);
			// Get a backup steam id by its name.
			String backupId = inputStreams.getInputStreamIdByName(receiver_back_up_name, receiver_ip, recever_uiport, receiver_user_name, receiver_password);
			
			// Cache a "delayed" results.
			ArrayList<Long> results = new ArrayList<Long>(); 
			
			// This test driver assists to add a backup source to a receiver output stream. 
			ReceiverUdpOutCreationDriver receiverUdpOutCreationDriver = new ReceiverUdpOutCreationDriver();
			
			
			Thread.sleep(30000);
			
			// Add a backup stream to a receiver output stream. 
			String addBackUpResults = receiverUdpOutCreationDriver.addBackupToOut(receiver_ip,recever_uiport,receiver_user_name,receiver_password,mainId,backupId);
			// Test a result of a of adding backup operation.
			if(!addBackUpResults.equals("Input assigned to output stream."))
				throw new Exception();
			
			Thread.sleep(30000);
			
			for(int i = 0 ; i < 20; i++)
			{
				// Implements Runnable interface, will run a UDP server which 
				ExternalRunnerThread externalRunnerThread = new ExternalRunnerThread(((RedundantFeederOneBxDriver)redundantFeederOneBxDriver), udp_port);
				
				// Start to listen to a UDP input traffic.
				externalRunnerThread.start();
				Thread.sleep(2000);
				// Stop the broadcasetr server
				sshJcraftClient.performCommand(sshUser, sshPassword, middle_bx_login_ip, sshPort, command);
				
				// Suspend the main thread (current thread) till the  UDP server will finish -- it waits at least 40 seconds or till the UDP packet will be received 
				// on a wire on an UDP server side. 
				externalRunnerThread.join();
				long result = externalRunnerThread.getResults();
				results.add(i, result);
				sshJcraftClient.performCommand(sshUser, sshPassword, middle_bx_login_ip, sshPort, "service zixibc start");
				Thread.currentThread().sleep(100000);
			}
			long statistics[] = StreamStatisticAnalyzer.getMaxMinAvgLong(results);
			System.out.println("Max is " + statistics[0] + "Min is " + statistics[1] + "Avg is " + statistics[2]);
		}
	
}
