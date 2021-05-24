package com.cj4dplex.test.client;

import java.io.BufferedReader;
import java.net.Socket;

public class ClientReceive extends Thread {

	Socket client;
	BufferedReader bufferedreader;
	String receivemsg;
	ClientGUI clientgui;

	ClientReceive(Socket s, BufferedReader br, ClientGUI cgui) {
		client = s;
		this.bufferedreader = br;
		this.clientgui = cgui;
	}

	public void run() {

		try {
			while ((receivemsg = bufferedreader.readLine()) != null) {
				System.out.println(receivemsg);
				clientgui.textArea.append(receivemsg + "\n");

			}
		} catch (Exception e) {

		}
	}

}
