package udpConnection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Power_Board {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		DatagramSocket dataSocket = new DatagramSocket(8080);
		DatagramPacket dataReceiver = null;
		DatagramPacket dataSender = null;
		byte[] buf = new byte [90000];
		String sendMsgBack = null;
		
		System.out.println("initialize Power Board");
		while (true) {
			
			Thread.sleep(10000);
			
			// Message Sent to from Power board 
			dataReceiver = new DatagramPacket(buf, buf.length);
			
			dataSocket.receive(dataReceiver);
			
			String msg = new String(buf, 0, buf.length);
			
			System.out.println("Message Received From Managment Board:- " + msg);
			
			// Message Sent back from Power board 
			System.out.println("Sending the results...");
			if (msg.contains("ON")) {
				sendMsgBack = "ON";
				
			}
			if (msg.contains("INIT")) {
				sendMsgBack = "INIT";
			}
			if (msg.contains("STAT")) {
				if (msg.contains("ON")) {
					sendMsgBack = "STAT = ON";
				} else {
					sendMsgBack = "STAT = OFF";
				}
			}
			if (msg.contains("OFF")) {
				sendMsgBack = "OFF";
				dataSocket.close();
				break;
			}
			
			
			buf = sendMsgBack.getBytes();
						
			int port = dataReceiver.getPort();
			
			dataSender = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), port);
			dataSocket.send(dataSender);
			
			
		}
		
	}

}
