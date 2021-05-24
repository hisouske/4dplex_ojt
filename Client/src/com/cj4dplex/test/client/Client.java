package com.cj4dplex.test.client;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	Socket socket = null;
	ClientReceive clientreceive;
	PrintWriter printwriter;
	BufferedReader bufferedreader;
	ClientGUI clientgui;

	public Client(ClientGUI clientgui) {
		try {
			this.clientgui = clientgui;
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 8888));
			System.out.println("[연결성공 (client)]");

			printwriter = new PrintWriter(socket.getOutputStream(), true);
			bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			clientreceive = new ClientReceive(socket, bufferedreader, clientgui);
			clientreceive.start();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void sendMsg(String sendmsg) throws IOException {
		if (printwriter != null) {
			printwriter.print(sendmsg + "\n");
			printwriter.flush();

		}
	}

	public void endMsg() throws IOException {
		printwriter.print("종료!");

		printwriter.close();
		bufferedreader.close();
		socket.close();
		clientreceive.stop();
		clientgui.setVisible(false);
	}

}
