package udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;
import udpClient.TcpUdpSend;

public class udpClient {
	public static String serverIP = "localhost";
	public static int port = 6000;
	public static int bufferSize = 1024;

	public static void main(String[] args) {
		DatagramSocket socket = null;
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			socket = new DatagramSocket();
			Thread thread = new Thread(TcpUdpReceive.UdpReceive(socket), "");
			thread.start();
			while (true) {
				System.out.print(">>");
				String message = scanner.nextLine();
				TcpUdpSend.UdpSend(message, port);
				
				// byte[] sendData = message.getBytes("utf-8");
				// DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
				// new InetSocketAddress(serverIP, port));
				// socket.send(sendPacket);
				//
				// DatagramPacket receivePacket = new DatagramPacket(new byte[bufferSize],
				// bufferSize);
				// socket.receive(receivePacket);
				// message = new String(receivePacket.getData(), 0, receivePacket.getLength(),
				// "UTF-8");
				// System.out.println("<<" + message);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
			scanner.close();
		}
	}
}