package exercise012;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class MWST_Server implements MWST_RemoteInterface {
	public BigDecimal calculateMwst(Collection<MWST_Article> articles) {
		BigDecimal total = new BigDecimal(0);
		for (MWST_Article art : articles) {
			total.add(art.value);
		}
		return total;
	}
	public static void main(String[] args) {
		try{
			MWST_Server mwst = new MWST_Server();
			MWST_RemoteInterface stub = (MWST_RemoteInterface) UnicastRemoteObject.exportObject(mwst, PORT);
	           Registry registry = LocateRegistry.createRegistry(PORT);
	           registry.rebind(NAME, stub);
	            System.out.println("HelloServer bound on"+PORT);
		} catch (Exception E){
	        System.err.println("HelloServer exception:");
	        E.printStackTrace();			
		}
	}
}
