package com.cj4dplex.test.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.TreeMap;

public class UserThread extends Thread {
	TreeMap<Integer, Socket> mysocketlist;
	int id;
	ServerGUI servergui;
	BufferedReader bufferedreader;
	PrintWriter printwriter;
	String receivemsg;

	UserThread(int i, TreeMap<Integer, Socket> socketlist, ServerGUI sgui) {
		this.id = i;
		mysocketlist = socketlist;
		servergui = sgui;

	}

	@Override
	public void run() {

		try {
			bufferedreader = new BufferedReader(new InputStreamReader(mysocketlist.get(id).getInputStream()));

			while ((receivemsg = bufferedreader.readLine()) != null) {
				if (receivemsg.equals("종료!")) {
					mysocketlist.remove(id);
					allSend("번 클라이언트 종료]");
					servergui.chatarea.append(id + "번  종료!" + "\n");
					Iterator<Integer> keys = mysocketlist.keySet().iterator();

					servergui.userarea.setText(null);
					while (keys.hasNext()) {
						int key = keys.next();
						servergui.userarea.append(Integer.toString(key) + "\n");
					}

					bufferedreader.close();
					mysocketlist.get(id).close();
				}
				servergui.chatarea.append(id + "번 ] :" + receivemsg + "\n");
				allSend(receivemsg);
			}
		} catch (Exception e) {
			System.out.println("--" + id + "user OUT");
		}

	}

	public void allSend(String rmsg) throws IOException {
		for (Integer i : mysocketlist.keySet()) {

			printwriter = new PrintWriter(mysocketlist.get(i).getOutputStream(), true);
			printwriter.print(id + "번 ] :" + rmsg + "\n");
			printwriter.flush();
		}
	}

}
