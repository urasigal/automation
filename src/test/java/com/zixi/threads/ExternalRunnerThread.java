package com.zixi.threads;

import com.zixi.drivers.RedundantFeederOneBxDriver;

public class ExternalRunnerThread extends Thread {
	
	private String udp_port;
	private RedundantFeederOneBxDriver redundantFeederOneBxDriver;
	
	public long getResults() {
		return results;
	}

	private long results;
	
	// Constructor of the class. Gets a RedundantFeederOneBxDriverobject as a parameter and a UDP port on which a UDP server will listen to.
	public ExternalRunnerThread(RedundantFeederOneBxDriver redundantFeederOneBxDriver, String udp_port)
	{
		this.udp_port = udp_port;
		this.redundantFeederOneBxDriver = redundantFeederOneBxDriver;
	}

	public void run()
	{
		results = redundantFeederOneBxDriver.testIMPL(Integer.parseInt(udp_port));
	}
}
