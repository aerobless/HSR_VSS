package exercise01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DaytimeClientSocketChannel {
	public static void main(String[] args) throws Exception {
		udpTest();
		System.out.println("ddd");
		String host = "time.nist.gov";
		int port = 13;

		// Connection to server
		Socket server;
		try {

			server = new Socket();
			server.connect(new InetSocketAddress(host, port));

			System.out.println("client> Connected to "
					+ server.getInetAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(
					server.getInputStream()));
			String serverMessage = in.readLine();
			System.out.println("client> Server said: " + serverMessage);
			server.close();
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}

	public static void udpTest() throws Exception{
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
		DatagramSocket clientSocket = new DatagramSocket(); 
		InetAddress IPAddress = InetAddress.getByName("time.nist.gov");       
		//byte[] sendData = new byte[1024];      
		byte[] receiveData = new byte[1024];      
		String sentence = inFromUser.readLine();       
		//sendData = sentence.getBytes();      
		//DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);      
		//clientSocket.send(sendPacket);      
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);      
		clientSocket.receive(receivePacket);     
		String modifiedSentence = new String(receivePacket.getData());      
		System.out.println("FROM SERVER:" + modifiedSentence);   
		clientSocket.close(); 
	}
}
