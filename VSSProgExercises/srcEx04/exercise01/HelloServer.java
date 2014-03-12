package exercise01;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer implements Hello {

    @Override
    public String getHello() throws RemoteException {
        return "Hello RMI";
    }

    public static void main(String[] args) {
        try {
            HelloServer hello = new HelloServer();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(hello, PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind(NAME, stub);
            System.out.println("HelloServer bound on"+PORT);
        } catch (Exception e) {
            System.err.println("HelloServer exception:");
            e.printStackTrace();
        }
    }
}
