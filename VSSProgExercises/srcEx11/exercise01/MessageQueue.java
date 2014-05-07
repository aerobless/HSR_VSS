package exercise01;

public class MessageQueue 

{
	int entries;
	int maxEntries;
	Object[] elements;

	public MessageQueue(int m) {
		maxEntries = m;
		elements = new Object[maxEntries];
		entries = 0;
	}

	public synchronized void send(Object x) throws InterruptedException {
		while (entries == maxEntries)
			wait();
		
		elements[entries] = x;
		entries = entries + 1;
		notifyAll();
	}

	public synchronized Object receive() throws InterruptedException {

		while (entries == 0)
			wait();

		Object x;
		x = elements[0];

		for (int i = 1; i < entries; i++) {
			elements[i - 1] = elements[i];
		}

		entries = entries - 1;
		notifyAll();
		
		return x;
	}

}
