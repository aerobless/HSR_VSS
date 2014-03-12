package exercise013;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Wakeupable extends Remote  {
    void wakeup() throws RemoteException;
}
