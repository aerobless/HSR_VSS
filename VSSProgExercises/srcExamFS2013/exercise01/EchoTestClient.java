package exercise01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoTestClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 7777;
		
		//Connection to server
		Socket server;
		try {

			server = new Socket();
			// Connection Timeout of 500 MS
			server.setSoTimeout(500);
			server.connect(new InetSocketAddress(host, port));
					
			System.out.println("client> Connected to " + server.getInetAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String serverMessage = in.readLine();
			System.out.println("client> Server said: "+ serverMessage);
			server.close();
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}
}
