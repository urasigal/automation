package com.zixi.g1050test;

import org.testng.Assert;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushG1050Driver;

public class BroadcasterPushG1050Test {
	public static void main(String [] args){
		
		BroadcasterPushG1050Driver testDriver = new BroadcasterPushG1050Driver();
		
		String maxwellProcessId = BroadcasterPushG1050Driver.
		getPid("maxwell", "maxwell", "10.7.0.70", "22", "pidof stdiserver")
		.split("\\n")[0];
		
		String killMaxwellAnswer = BroadcasterPushG1050Driver.
		getPid("root",  "maxwell",  "10.7.0.70",  "22",  "kill -9 " + maxwellProcessId);
		
		String startMaxwell = BroadcasterPushG1050Driver.
		getPid("root", "maxwell", "10.7.0.70", "22", 
		"stdiserver -L /usr/local/lib/iwl/plugins/itu_t_g_1050_3.so");
	}
}
