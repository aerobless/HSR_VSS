import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex02_1_HelloWorldServer {

	public static void main(String[] args) throws IOException {
		int port = 2342;
		
		ServerSocket server = new ServerSocket(port);
		
		while(true){
			System.out.println("server> Waiting for client...");
			Socket client = server.accept();
			System.out.println("sever> Client from "+ client.getInetAddress()+ " connected.");
			
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			//Date date = new Date();
			out.println("Hello World! - My adress"+server.getInetAddress()+" your address: "+client.getInetAddress());
		}
	}
}
