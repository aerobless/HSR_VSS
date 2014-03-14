package exercise012;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;

public class MWST_Client {
	
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(MWST_RemoteInterface.HOST, MWST_RemoteInterface.PORT);
            MWST_RemoteInterface mwst = (MWST_RemoteInterface) registry.lookup(MWST_RemoteInterface.NAME);
            
            //Create articles:
            MWST_Article art1 = new MWST_Article("M-Budget EnergyDrink", BigDecimal.valueOf(0.7));
            MWST_Article art2 = new MWST_Article("RedBull", BigDecimal.valueOf(2.2));
            MWST_Article art3 = new MWST_Article("Coca Cola", BigDecimal.valueOf(1.2));
            
            Collection<MWST_Article> articles =  new ArrayList<MWST_Article>();          
            articles.add(art1);
            articles.add(art2);
            articles.add(art3);
  
            //note to future self: we were required to use BigDecimal.. BigDecimal sucks
            System.out.println("Answer from server received:");
            System.out.println(mwst.calculateMwst(articles));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
