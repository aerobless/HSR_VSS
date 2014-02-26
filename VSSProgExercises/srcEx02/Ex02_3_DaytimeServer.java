import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex02_3_DaytimeServer {
	static class DayTimeThread extends Thread {
		Socket inputSocket;
		
		
	public DayTimeThread(Socket aClient) {
			super();
			inputSocket = aClient;
		}

		@Override
		public void run() {
			try {
				PrintWriter out = new PrintWriter(inputSocket.getOutputStream(), true);
	
				//Sleeping for 5seconds so that we can be sure multiple threads 
				//get opend by fast requests.
				System.out.println("Currently working on reporting time & date");
				Thread.sleep(5000);
				Date date = new Date();
				out.println(date);
				inputSocket.close();
			} catch (IOException anEx) {
				// TODO Auto-generated catch block
				anEx.printStackTrace();
			} catch (InterruptedException anEx) {
				// TODO Auto-generated catch block
				anEx.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int port = 1313; //daytime usually uses port 13, but that would require sudo
		
		/**More info about the daytime protocol under:
		 * https://tools.ietf.org/html/rfc867
		 */
		
		while(true){
			ServerSocket server = new ServerSocket(port);
			System.out.println("server> Waiting for client...");
			
			Socket client = new Socket();
			client.setSoTimeout(100);
			client = server.accept();
			
			DayTimeThread dayTimeResponse = new DayTimeThread(client);
			dayTimeResponse.start();
			
			//Report active Threads
			System.out.println("Active Threads: "+Thread.activeCount());
			
			server.close();
		}
	}

}
