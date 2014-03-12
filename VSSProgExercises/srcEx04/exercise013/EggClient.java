package exercise013;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EggClient implements Wakeupable, Serializable {
	private static final long serialVersionUID = -8643511179373249635L;

	public static void main(String[] args) {
		EggClient client = new EggClient();
        try {
            Registry registry = LocateRegistry.getRegistry(Timer.HOST, Timer.PORT);
            Timer timer = (Timer) registry.lookup("egg"); //name ?!
            
            System.out.println("Answer from server received:");
            timer.setTimer(1, client); //we have to give us to the server
            while(true){
    			Thread.sleep(500);
    			System.out.println("staying alive");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void wakeup() throws RemoteException {
		System.out.println("yay I woke");
		EggDialog eggy = new EggDialog();
	}
}
