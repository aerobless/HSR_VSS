import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Ex02_1_HelloWorldClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 2342;
		
		//Connection to server
		Socket server;
		try {
			server = new Socket();
			// Connection Timeout of 500 MS
			server.connect(new InetSocketAddress(host, port), 100);
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
