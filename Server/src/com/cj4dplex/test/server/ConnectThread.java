package com.cj4dplex.test.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import com.cj4dplex.test.server.UserThread;

public class ConnectThread extends Thread {
	private static ConnectThread conthread = null;
	private static TreeMap<Integer, Socket> socketlist = new TreeMap<>();
	private ServerSocket serverSocket;
	private int userNum = 1;
	private ServerGUI servergui = new ServerGUI();

	private boolean flag = true;

	private ConnectThread() {
		try {
			servergui.setVisible(true);
			serverSocket = new ServerSocket(8888);

			System.out.println("---server waiting---");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static final ConnectThread getInstance() {
		if (conthread == null) {
			synchronized (ConnectThread.class) {
				if (conthread == null) {
					conthread = new ConnectThread();
				}
			}
		}
		return conthread;
	}

	@Override
	public void run() {
		try {

			while (flag) {
				Socket socket = serverSocket.accept();
				System.out.println("<고객번호" + userNum + "번> login!" + socket);

				socketlist.put(userNum, socket);

				Iterator<Integer> keys = socketlist.keySet().iterator();
				servergui.userarea.setText(null);
				while (keys.hasNext()) {
					int key = keys.next();
					servergui.userarea.append(Integer.toString(key) + "\n");
				}

				UserThread userThread = new UserThread(userNum, socketlist, servergui);
				userThread.start();
				userNum++;
			}
		} catch (IOException e) {
			System.out.println(">SERVER CLOSED");

		}
	}

}
