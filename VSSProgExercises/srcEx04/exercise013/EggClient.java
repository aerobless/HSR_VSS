package exercise013;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EggClient implements Wakeupable, Serializable {
	private static final long serialVersionUID = -8643511179373249635L;
	private static boolean awaitingWaking = true;

	public static void main(String[] args) {
		EggClient client = new EggClient();
        try {
            Registry registry = LocateRegistry.getRegistry(Timer.HOST, Timer.PORT);
            Timer timer = (Timer) registry.lookup("egg"); //name ?!
            
            timer.setTimer(1, client);
            
            while(awaitingWaking){
    			Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void wakeup() throws RemoteException {
		System.out.println("yay I woke");
		  JOptionPane.showMessageDialog(new JFrame("Global Error Handler"), "Time's up! No more sleepy sleepy!");
		  awaitingWaking = false;
	}
}
