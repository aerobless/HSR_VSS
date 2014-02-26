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
			server.setSoTimeout(500);
			server.connect(new InetSocketAddress(host, port));
			
			// To provoke SERVER timeout, Thread.sleep(10000) here.
			try {
				Thread.sleep(10000);
			} catch (InterruptedException anEx) {
				// TODO Auto-generated catch block
				anEx.printStackTrace();
			}
			
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
