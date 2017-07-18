package com.zixi.g1050test;

import org.testng.Assert;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushG1050Driver;

public class BroadcasterPushG1050Test {
	public static void main(String [] args){
		
		BroadcasterPushG1050Driver testDriver = new BroadcasterPushG1050Driver();
		
		String maxwellProcessId = BroadcasterPushG1050Driver.
		getPid("maxwell",  "maxwell",  "10.7.0.70",  "22",  "pidof stdiserver")
		.split("/")[0];
		
		String killMaxwellAnswer = BroadcasterPushG1050Driver.
		getPid("maxwell",  "maxwell",  "10.7.0.70",  "22",  "suso kill -9 " + maxwellProcessId)
		.split("/")[0];
	}
}
