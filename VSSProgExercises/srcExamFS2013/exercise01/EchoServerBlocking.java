package exercise01;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerBlocking {
	private static final String HELLO_REPLY = "Hello World!\n";
	private static final int PORT = 7777;

	public static void main(String[] args) {
		ServerSocket ssc = null;
		try {
			ssc = new ServerSocket();
			ssc.bind(new InetSocketAddress(PORT));
			while(true){
				Socket sc = ssc.accept();
				
				if(sc == null){
					System.out.println("HelloServer: sleeping...");
					try{
						Thread.sleep(3000);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				} else {
					//Sleeping to provoke blocking
					try{
						Thread.sleep(3000);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
					System.out.println("Client: "+ sc.getRemoteSocketAddress());
					System.out.println("printRequest(sc) here");
					OutputStream os = sc.getOutputStream();
					os.write(HELLO_REPLY.getBytes());
					os.flush();
					//sc.close();
						
				}
			}
		} catch (IOException e){
			System.out.println(e);
		} finally {
			if(ssc!= null){
				try{
					ssc.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}

	}

}
