import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex02_2_HTTPGetClient {

	public static void main(String[] args) {
		get("blood-cloud.com");	
	}
	
	/**
	 * Custom implementation of HTTP get for exercise 2 in VSS.
	 * Infos about HTTP: https://www.ietf.org/rfc/rfc2616.txt
	 * @param webURL
	 */
	public static void get(String webURL) {
		Socket s;
		try {
			s = new Socket(InetAddress.getByName(webURL), 80);
			PrintWriter printer = new PrintWriter(s.getOutputStream());
			printer.println("GET / HTTP/1.1");
			printer.println("Host: "+webURL);
			printer.println("");
			//Empty line required so that the webserver knows the request is over.
			printer.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String lineOutput;
			while((lineOutput = br.readLine()) != null) {
				System.out.println(lineOutput);
			}
			br.close();
		} catch (UnknownHostException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}

}
