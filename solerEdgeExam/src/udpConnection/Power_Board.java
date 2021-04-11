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
		byte[] buffer = new byte[1024];

		String sendON = "ON";
		String sendINIT = "INIT";
		String sendSTAT = "STAT";
		String sendOFF = "OFF";
		
		System.out.println("initialize Power Board");
		while (true) {
			
//			Thread.sleep(10000);
			
			// Message Sent to from Power board 
			dataReceiver = new DatagramPacket(buffer, buffer.length);
			
			dataSocket.receive(dataReceiver);
			
			byte[] data = new byte[dataReceiver.getLength()];
			System.arraycopy(dataReceiver.getData(), dataReceiver.getOffset(), data, 0, dataReceiver.getLength());

			String msg = new String(data);
			
			System.out.println("Message Received From Managment Board:- " + msg);
			
			if(msg.equals("ON"))
            {
				buffer = sendON.getBytes();
				int port = dataReceiver.getPort();				
				dataSender = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), port);
				dataSocket.send(dataSender);
				System.out.println("message sent to managament board: - " + sendON);
				
            }
            else if (msg.equals("IN") || msg.equals("INIT")){
            	
            	buffer = sendINIT.getBytes();
				int port = dataReceiver.getPort();				
				dataSender = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), port);
				dataSocket.send(dataSender);
				System.out.println("message sent to managament board: - " + sendINIT);
            }
            	else if (msg.equals("ST") || msg.equals("STAT")){ 	
            	buffer = sendSTAT.getBytes();
				int port = dataReceiver.getPort();				
				dataSender = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), port);
				dataSocket.send(dataSender);
				System.out.println("message sent to managament board: - " + sendSTAT);
            }
            	else if (msg.equals("OFF")){ 	
                	buffer = sendOFF.getBytes();
    				int port = dataReceiver.getPort();				
    				dataSender = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), port);
    				dataSocket.send(dataSender);
    				System.out.println("message sent to managament board: - " + sendOFF);
    				dataSocket.close();
    				break;
                }
			
		

			
			
			
		}
		
	}

}
