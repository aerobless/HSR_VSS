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
			Socket client = new Socket();
			client.setSoTimeout(300);
			client = server.accept();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException anEx) {
				// TODO Auto-generated catch block
				anEx.printStackTrace();
			}
			System.out.println("sever> Client from "+ client.getInetAddress()+ " connected.");
			
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println("Hello World! - My adress"+server.getInetAddress()+":"+server.getLocalPort()+" your address: "+client.getInetAddress());
			client.close();
			server.close();
		}
	}
}
