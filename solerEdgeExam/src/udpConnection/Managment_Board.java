package udpConnection;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Managment_Board {
	
	private static String msg;
	private static byte buf[];
	private static DatagramSocket ds;
	private static DatagramPacket dp;
	private static JLabel label;
	
	public static void GUI() throws IOException{
	JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Managment Board");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
    JButton on = new JButton("ON");      
    JButton init = new JButton("INIT");
    JButton status = new JButton("STATUS");          
    JButton off = new JButton("OFF");          
    
    label = new JLabel("Message Displayed from PowerBoard");
    label.setBorder(BorderFactory.createEmptyBorder(40,40, 40, 40));
    label.setForeground(Color.white);
    
    on.setForeground(Color.green);
    off.setForeground(Color.red);
    init.setForeground(Color.blue);
    status.setForeground(Color.MAGENTA);
    
    JPanel panel = new JPanel();
    panel.setBackground(Color.DARK_GRAY);
    panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
    panel.setLayout(new FlowLayout());
    panel.add(on);
    panel.add(init);
    panel.add(status);
    panel.add(off);
    panel.add(label);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);  
    
    

    on.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ds = new DatagramSocket();
				InetAddress ip = InetAddress.getLocalHost();
				msg = "ON";
				
				buf = msg.getBytes();
				dp = new DatagramPacket(buf, buf.length, ip, 8080);
				ds.send(dp);
				
				DatagramPacket dpReceiver = new DatagramPacket(buf, 0, buf.length);
				ds.receive(dpReceiver);
				
				String msgReceived = new String(dpReceiver.getData(),0,dpReceiver.getLength());
				System.out.println("Power board sends:- " + msg);
				label.setText("MODE:- " + msgReceived);
				
				if (msgReceived != null) {
					System.out.println("Message:- " + msgReceived);
					System.out.println("connection ended...");
				}
			} catch (IOException e1) {
				System.out.println("Error has occured");
				e1.printStackTrace();
			}
			ds.close();
		}
	});
    
    init.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ds = new DatagramSocket();
				InetAddress ip = InetAddress.getLocalHost();
				msg = "INIT";
				label.setText("Device Set:- " + msg);
				
				buf = msg.getBytes();
				dp = new DatagramPacket(buf,0, buf.length, ip, 8080);
				ds.send(dp);
				
				
				DatagramPacket dpReceiver = new DatagramPacket(buf,0, buf.length);
				ds.receive(dpReceiver);
				String msgReceived = new String(dpReceiver.getData(),0,dpReceiver.getLength());
				System.out.println("Power board sends:- " + msg);
				label.setText("MODE:- " + msgReceived);
				
				if (msgReceived != null) {
					System.out.println("Message:- " + msgReceived);
					System.out.println("connection ended...");
				}
			  
			} catch (IOException e1) {
				System.out.println("Error has occured");
				e1.printStackTrace();
			}
			ds.close();
		}
	});
    
    status.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ds = new DatagramSocket();
				InetAddress ip = InetAddress.getLocalHost();
				msg = "STAT";
				while (true) {
				buf = msg.getBytes();
				dp = new DatagramPacket(buf, buf.length, ip, 8080);
				ds.send(dp);
				
				DatagramPacket dpReceiver = new DatagramPacket(buf, buf.length);
				ds.receive(dpReceiver);
				
				String msgReceived = new String(dpReceiver.getData(),0,dpReceiver.getLength());
				System.out.println("Power board sends:- " + msg);
				label.setText("MODE:- " + msgReceived);
				
				if (msgReceived != null) {
					System.out.println("Message:- " + msgReceived);
					System.out.println("connection ended...");
					break;
				}
			  }
			} catch (IOException e1) {
				System.out.println("Error has occured");
				e1.printStackTrace();
			}
			ds.close();
		}
	});
    
    
    
    off.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("You have Pressed OFF");
			try {
				
				ds = new DatagramSocket();
				InetAddress ip = InetAddress.getLocalHost();
				msg = "OFF";
				buf = msg.getBytes();
				dp = new DatagramPacket(buf, buf.length, ip, 8080);
				ds.send(dp);
				ds.close();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.dispose();
				
			} catch (IOException e1) {
				System.out.println("Error has occured");
				e1.printStackTrace();
			}	
		}
	});
    
 }
	

	
	public static void main(String[] args) throws IOException {
		GUI();
	}
}