package exercise012;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;


/**
 * Don't forget to implement the RemoteInterface class.
 */
public class MWST_Server implements MWST_RemoteInterface {
	
	//If Override doesn't work, you haven't properly implemented the remote interface!
	@Override
	public BigDecimal calculateMwst(Collection<MWST_Article> articles) throws RemoteException {
		double total = 0;
		for (MWST_Article art : articles) {
			total += art.value.doubleValue();
			total = total*1.08;
			System.out.println(art.name+" "+art.value);
		}
		System.out.println("ServerConsole: "+total);
		return new BigDecimal(total);
	}
	
	
	public static void main(String[] args) {
		try{
			MWST_Server mwst = new MWST_Server();
			//If you can't directly use "PORT" etc. then you haven't properly implemented the remote interface.
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
