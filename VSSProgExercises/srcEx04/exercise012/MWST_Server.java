package exercise012;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class MWST_Server implements MWST_RemoteInterface {
	
	@Override
	public BigDecimal calculateMwst(Collection<MWST_Article> articles) throws RemoteException {
		double total = 0;
		for (MWST_Article art : articles) {
			total += art.value.doubleValue();
			System.out.println(art.name+" "+art.value);
		}
		System.out.println("ServerConsole: "+total);
		return new BigDecimal(total);
	}
	public static void main(String[] args) {
		try{
			MWST_Server mwst = new MWST_Server();
			MWST_RemoteInterface stub = (MWST_RemoteInterface) UnicastRemoteObject.exportObject(mwst, PORT);
	           Registry registry = LocateRegistry.createRegistry(PORT);
	           registry.rebind(NAME, stub);
	            System.out.println("MWST-Server bound on "+PORT);
	            System.out.println("------------------------------");
		} catch (Exception E){
	        System.err.println("MWST-Server exception:");
	        E.printStackTrace();			
		}
	}
}