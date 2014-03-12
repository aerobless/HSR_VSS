package exercise013;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Client also has to implement an Interface that the server knows, so that it can be called-back. Also
 * it's easy to implement multiple interface *duh!* just add a god-damn comma.
 */
public class EggClient implements Wakeupable, Serializable {
	private static final long serialVersionUID = -8643511179373249635L;
	private static boolean awaitingWaking = true;

	public static void main(String[] args) {
		//Give this class a name, so we can give it to the server
		EggClient client = new EggClient();
        try {
            Registry registry = LocateRegistry.getRegistry(Timer.HOST, Timer.PORT);
            Timer timer = (Timer) registry.lookup("egg");
 
            //The server needs the time and this client so it knows when and what to wake.
            timer.setTimer(20, client);
            System.out.println("Request to server successfully sent, waiting to be woken..");
            //Keep the client alive while it's waiting for the server to wake it.
            while(awaitingWaking){
    			Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	//Called by the server once the timer is up, implemented through "Wakupable"
	@Override
	public void wakeup() throws RemoteException {
		System.out.println("*YAY* I got the wakeup-call!");
		  JOptionPane.showMessageDialog(new JFrame("Global Error Handler"), "Time's up! No more sleepy sleepy!");
		  awaitingWaking = false;
	}
}
