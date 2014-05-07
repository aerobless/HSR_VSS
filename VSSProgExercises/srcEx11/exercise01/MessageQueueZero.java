package exercise01;

public class MessageQueueZero
 
{
	boolean sendArrived, receiveArrived;

	Object share;

	public MessageQueueZero() {
		sendArrived = false;
		receiveArrived = false;
	}

	public synchronized void send(Object x) throws InterruptedException {
		sendArrived = true;
		share = x;
		notifyAll();
		while (!receiveArrived)
			wait();

		receiveArrived = false;
	}

	public synchronized Object receive() throws InterruptedException {
		receiveArrived = true;
		notifyAll();

		while (!sendArrived)
			wait();

		Object x;
		x = share;
		sendArrived = false;
		return x;
	}
}
