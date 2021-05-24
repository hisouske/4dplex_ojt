package com.cj4dplex.test.udpServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udpServer {

	public static int port = 6000;
	public static int bufferSize = 1024;

	public static void main(String[] args) {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(port);
			DatagramPacket receivePacket = new DatagramPacket(new byte[bufferSize], bufferSize);
			while (true) {
				socket.receive(receivePacket);
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println(message);
				System.out.println(receivePacket.getPort());			
				byte[] sendData = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(),
						receivePacket.getPort());
				socket.send(sendPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}
