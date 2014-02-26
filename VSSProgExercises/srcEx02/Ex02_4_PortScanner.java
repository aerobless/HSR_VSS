import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex02_4_PortScanner {
	
	public static class PortScanWorker implements Runnable {
		String host;
		int port;
		boolean reportOnlyOpen;

		public PortScanWorker(String aHost, int aPort, boolean aReportOnlyOpen) {
			super();
			host = aHost;
			port = aPort;
			reportOnlyOpen = aReportOnlyOpen;
		}

		@Override
		public void run() {
			if (scanSpecificPort(host, port)){
				System.out.println("Port: "+port+" on "+host+" is open");	
			}
			else if(reportOnlyOpen == false) {
				System.out.println("Port: "+port+" on "+host+" is closed");	
			}
		}
	}

	public static void main(String[] args) {
		//Parmeters:
		String host = "blood-cloud.com";
		boolean reportOnlyOpen = true;
		int startPort = 0;
		int endPort = 1000;
		int threadCount = 100;
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		for (int i = startPort; i < endPort; i++){
			Runnable scanWorker = new PortScanWorker(host, i, reportOnlyOpen);
			executor.execute(scanWorker);
		}
		executor.shutdown();
	}
	
	public static boolean scanSpecificPort(String host, int port) {
        try {
            Socket socket = new Socket();
            socket.setSoTimeout(200);
            socket.connect(new InetSocketAddress(host, port));
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
