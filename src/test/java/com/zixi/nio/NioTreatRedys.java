package com.zixi.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;



public class NioTreatRedys implements Runnable {

	protected NioHandler handler;

	// Constructor
	public NioTreatRedys(NioHandler handler) {
		this.handler = handler;
	}

	//
	public void setHandlerIteratorOfSelectedKeys(Deque<SelectionKey> deque) {
		this.handler.setSelectedKeys(deque);
	}

	public void setPendingData(Map pendingData) {
		this.handler.setPendingData(pendingData);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) 
		{
			try {
				handler.treatRedy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
