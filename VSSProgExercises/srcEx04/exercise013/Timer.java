package exercise013;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Timer extends Remote {
	public static final String HOST = "localhost";
	public static final int PORT = 1099;

	void setTimer(int time, Wakeupable target) throws RemoteException;
}
