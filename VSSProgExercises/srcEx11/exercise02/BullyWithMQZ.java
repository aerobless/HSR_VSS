package exercise02;

import java.io.IOException;

import exercise01.MessageQueueZero;
 
public class BullyWithMQZ {
	static class Message {
		String type;

		Participant candidate;

		Message(String t, Participant p) {
			type = t;
			candidate = p;
		}
	}

	static class Participant extends Thread {
		MessageQueueZero inbox;

		MessageQueueZero[] neighbour;

		int value;

		Participant boss;

		Participant me;

		public void run() {
			boss = this;
			me = this;
			(new Thread() {
				public void run() {
					for (int i = 0; i < neighbour.length; i++)
						try {
							neighbour[i].send(new Message("election", me));
						} catch (Exception e) {
						}
				}
			}).start();
			try {
				for (;;) {
					Message m = (Message) inbox.receive();
					System.out.println(value + " receives " + m.type + " "
							+ m.candidate.value);

					if (m.type == "election") {
						if (m.candidate.value > boss.value)
							boss = m.candidate;
					}
				}
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) throws IOException {
		final int n = 9;
		final int[] value = { 11, 9, 17, 6, 15, 19, 2, 12, 7 };
		Participant[] part = new Participant[n];
		MessageQueueZero[] q = new MessageQueueZero[n];

		for (int i = 0; i < n; i++) {
			part[i] = new Participant();
			part[i].value = value[i];
			q[i] = new MessageQueueZero();
		}

		for (int i = 0; i < n; i++) {
			part[i].inbox = q[i];
			part[i].neighbour = new MessageQueueZero[n];
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				part[i].neighbour[j] = part[j].inbox;
			}
		}

		for (int i = 0; i < n; i++) {
			part[i].start();
		}

		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}

		for (int i = 0; i < n; i++) {
			part[i].interrupt();
		}

		for (int i = 0; i < n; i++) {
			if (part[i].boss != null)
				System.out.println(part[i].value + " boss : "
						+ part[i].boss.value);
		}
	}
}
