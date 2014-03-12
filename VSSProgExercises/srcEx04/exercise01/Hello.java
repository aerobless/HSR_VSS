package exercise01;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    public static final int PORT = 25533;
    public static final String HOST = "mc.blood-cloud.com";
    public static final String NAME = "Hello";

    public String getHello() throws RemoteException;
}