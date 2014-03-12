package exercise013;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EggServer implements Timer{

	public static void main(String[] args) {
		try{
			EggServer eggServ = new EggServer();
			Timer stub = (Timer) UnicastRemoteObject.exportObject(eggServ, PORT);
	           Registry registry = LocateRegistry.createRegistry(PORT);
	           registry.rebind("egg", stub);
	            System.out.println("Egg-Server bound on "+PORT);
	            System.out.println("------------------------------");
		} catch (Exception E){
	        System.err.println("Egg-Server exception:");
	        E.printStackTrace();			
		}
	}

	@Override
	public void setTimer(int aTime, Wakeupable aTarget) throws RemoteException {
		System.out.println("Setting timer to wake client");
		WakeUpThread waker = new WakeUpThread(aTime, aTarget);
		waker.start();
	}
}
