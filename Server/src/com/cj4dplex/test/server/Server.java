package com.cj4dplex.test.server;

import com.cj4dplex.test.server.ConnectThread;

public class Server {
	public static void main(String[] args) {

		ConnectThread connectThread = ConnectThread.getInstance();
		connectThread.start();
	}
}
