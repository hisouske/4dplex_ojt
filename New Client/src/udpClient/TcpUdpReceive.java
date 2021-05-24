package udpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TcpUdpReceive {

//	private static DatagramSocket socket = null;
	private static int bufferSize = 1024;

	public static final Runnable UdpReceive(DatagramSocket ds) {
		return new Runnable() {
			@Override
			public void run() {
				DatagramSocket socket = ds;
				DatagramPacket receivePacket = new DatagramPacket(new byte[bufferSize], bufferSize);
				while (true) {
					try {
						socket.receive(receivePacket);
						String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
						System.out.println(message);
						System.out.println(receivePacket.getPort());

					} catch (IOException e) {
						e.printStackTrace();

					}

				}

			}
		};
	}

	public static final Runnable TcpReceive() {
		return new Runnable() {
			@Override
			public void run() {

			}
		};
	}
}
