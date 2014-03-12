package exercise013;

import java.rmi.RemoteException;

public class WakeUpThread extends Thread {
	private int time;
	private Wakeupable client;

	public WakeUpThread(int time, Wakeupable client) {
		this.time = time;
		this.client = client;
	}

	public void run() {
		try {
			Thread.sleep(time * 1000);
		}
		catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
		try {
			System.out.println("time's up - trying to wake client now...");
			client.wakeup();
		}
		catch (RemoteException re) {
            re.printStackTrace();
		}
	}
}