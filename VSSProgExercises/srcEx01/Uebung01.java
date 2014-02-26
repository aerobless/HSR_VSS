import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uebung01 {

	public static void main(String[] args) {
		try {
			System.out.println("aufgabe3 startet");
			aufgabe3();
			System.out.println("aufgabe3 endet");
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
		/*
		try {
			downloadFileFromWeb("http://blood-cloud.com/images/logo_wb_hiya.png");
		} catch (IOException anEx1) {
			// TODO Auto-generated catch block
			anEx1.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		try {
			getWebInfos("http://www.hsr.ch");
			getWebInfos("http://de.selfhtml.org/html/tabellen/aufbau.htm#definieren");
			getWebInfos("https://unterricht.hsr.ch:443");
			getWebInfos("http://www.google.ch/#aq=1&aqi=g5&aql=&hl=en&q=hsr+rapperswil");
		} catch (MalformedURLException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
*/
	}
	
	public static void aufgabe01() {
		try {
			//Aufgabe a
			System.out.println(InetAddress.getLocalHost());
			
			//Aufgabe b
			System.out.println(InetAddress.getByName("sidv0012.hsr.ch"));
			System.out.println(InetAddress.getByName("152.96.21.12"));
			System.out.println(InetAddress.getByName("www.google.com"));
			InetAddress nameByIp = InetAddress.getByName("152.96.21.12");
			
			//Aufgabe c
			System.out.println("host:"+nameByIp.getCanonicalHostName());

			//Aufgabe d
			InetAddress[] servers = InetAddress.getAllByName("google.com");
			for(InetAddress address2 : servers){
			  System.out.println(address2.getHostAddress());
			}
			
		} catch (UnknownHostException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}
	
	//Aufgabe 2A
	public static void getWebInfos(String webURL) throws MalformedURLException {
		URL website = new URL(webURL);
		System.out.println("");
		System.out.println("-----------------"+webURL+"-----------------");
		System.out.println("Protocol: "+website.getProtocol());
		System.out.println("Host: "+website.getHost());
		System.out.println("Port: "+website.getPort());
		System.out.println("Path: "+website.getPath());
		System.out.println("File: "+website.getFile());
		System.out.println("Reference: "+website.getRef());
		System.out.println("---------------------------------------------");
		try {
			getWebContent(webURL);
		} catch (IOException anEx) {
			// TODO Auto-generated catch block
			anEx.printStackTrace();
		}
	}
	
	//Aufgabe 2B
	public static void getWebContent(String webURL) throws IOException {
		URL website = new URL(webURL);
		URLConnection con = website.openConnection();
		
		//Match charset
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		String charset = m.matches() ? m.group(1) : "ISO-8859-1";
		
		Reader websiteReader = new InputStreamReader(con.getInputStream(), charset);
		StringBuffer websiteBuffer = new StringBuffer();
		
		while(true) {
			int ch = websiteReader.read();
			if (ch < 0)
				break;
			websiteBuffer.append((char) ch);
		}
		websiteReader.close();
		System.out.println(websiteBuffer);
	}
	
	//Aufgabe 2C
	public static String downloadFileFromWeb(String webURL) throws IOException {
		URL fileAddressOnWeb = new URL(webURL);
		URLConnection con = fileAddressOnWeb.openConnection();
		
		//Get file
		DataInputStream downloader = new DataInputStream(con.getInputStream());
		byte[] fileArray = new byte[con.getContentLength()];
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = downloader.readByte();
		}
		downloader.close();

		//Write file
		String fileName = webURL.substring( webURL.lastIndexOf('/')+1, webURL.length());
		File downloadedFile = new File(fileName);
		FileOutputStream fileOS = new FileOutputStream(downloadedFile);
		fileOS.write(fileArray);
		fileOS.close();
		
		System.out.println(downloadedFile.getAbsolutePath());
		return downloadedFile.getAbsolutePath();
	
	}
	
	
	//Aufgabe 3
	public static void aufgabe3() throws IOException {
		URL serverAdress = new URL("http://127.0.0.1:9595");
		URLConnection con = serverAdress.openConnection();
		
		con.setDoOutput(true);
		con.connect();
		
		//DataOutputStream request = new DataOutputStream(con.getOutputStream());
		PrintWriter pw = new PrintWriter(con.getOutputStream());
	
		Map<String, String> data = new HashMap<String, String>();
		data.put("input", "test");
		
		//CREATE AWESOMELY GREAT URL
		Set<String> keys = data.keySet();
		Iterator<String> keyIter = keys.iterator();
		String content = "";
		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0) {
				content += "&";
			}
			content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
		}

		pw.println(content);
		pw.flush();
		pw.close();
		
		System.out.println("here we are");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	
		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println("output:"+line);
		}
		in.close();
	}
}
