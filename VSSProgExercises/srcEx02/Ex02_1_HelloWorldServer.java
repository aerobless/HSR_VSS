import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex02_1_HelloWorldServer {

	public static void main(String[] args) throws IOException {
		int port = 2342;
		
		while(true){
			ServerSocket server = new ServerSocket(port);
			System.out.println("server> Waiting for client...");

			// To provoke CLIENT timeout, Thread.sleep(10000) here.
			Socket client = new Socket();
			client.setSoTimeout(100);
			client = server.accept();

			System.out.println("sever> Client from "+ client.getInetAddress()+ " connected.");
			
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println("Hello World! - My adress"+server.getInetAddress()+":"+server.getLocalPort()+" your address: "+client.getInetAddress());
			client.close();
			server.close();
		}
	}
}
