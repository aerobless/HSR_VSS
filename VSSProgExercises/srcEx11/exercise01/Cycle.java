package exercise01;

import java.io.IOException;

public class Cycle {
	static class Message {
		String type;

		Participant candidate;

		Message(String t, Participant p) {
			type = t;
			candidate = p;
		}
	}

	static class Participant extends Thread {
		MessageQueue previous;

		MessageQueue next;

		int value;

		Participant boss;

		public void run() {
			try {
				for (;;) {
					Message m = (Message) previous.receive();
					System.out.println(value + " receives " + m.type + " "
							+ m.candidate.value);
					if (m.type == "election") {
						if (m.candidate.value > value)
							next.send(m);
						else if (m.candidate.value < value)
							next.send(new Message("election", this));
						else
							next.send(new Message("elected", this));
					} else {
						// m.type=="elected"
						boss = m.candidate;
						if (m.candidate.value == value)
							return;
						else
							next.send(m);
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
		MessageQueue[] q = new MessageQueue[n];
		for (int i = 0; i < n; i++) {
			part[i] = new Participant();
			part[i].value = value[i];
			q[i] = new MessageQueue(1);
		}

		for (int i = 0; i < n; i++) {
			part[i].previous = q[i];
			part[i].next = q[(i + 1) % n];
		}

		for (int i = 0; i < n; i++) {
			System.out.println(part[i].value + " next "
					+ part[(i + 1) % n].value);
			if (part[i].next != part[(i + 1) % n].previous)
				System.out.println("Connection Error");
		}

		for (int i = 0; i < n; i++) {
			part[i].start();
		}

		Participant p = part[0];
		try {
			p.next.send(new Message("election", p));
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		for (int i = 0; i < n; i++) {
			part[i].interrupt();
		}

		for (int i = 0; i < n; i++) {
			System.out
					.println(part[i].value + " boss : " + part[i].boss.value);
		}
	}
}
