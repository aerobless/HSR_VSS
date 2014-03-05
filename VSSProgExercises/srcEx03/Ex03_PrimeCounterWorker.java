import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_PrimeCounterWorker {
    private static final String TASK_QUEUE_NAME = "prime_queue";

    public static void main(String[] argv) throws Exception {
    	double randomID = Math.random() * 500 + 1;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        
		channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
		/*Telling the channel that we only want one message at a time. This makes it fair, so that other
		 * workers have a chance at getting a packet too.*/
		channel.basicQos(1);
        while (true) {
        	/*Getting a message*/
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            /*Getting the content of our PrimeCounterMessage*/
            Ex03_PrimeCounterMessage pm = Ex03_PrimeCounterMessage.fromBytes(delivery.getBody());
            /*Creating a new PrimeCounter utilizing our pm*/
    		PrimeCounter primer = new PrimeCounter(pm);
    		/*Manual acknowledgement: If missing we don't delete the message from the queue. This is helpful
    		 * in case our worker crashes before finishing the calculation. So another worker can pick up working on it.*/
    		channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    		System.out.println("Primes-Calculator"+randomID+": "+primer.countPrimes());
        }
    }

    private static class PrimeCounter {
        private final long start;
        private final long end;

        PrimeCounter(Ex03_PrimeCounterMessage message) {
            this.start = message.start;
            this.end = message.end;
        }

        public long countPrimes() {
            long count = 0;
            for (long number = start; number < end; number++) {
                if (isPrime(number)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isPrime(long number) {
            for (long factor = 2; factor * factor <= number; factor++) {
                if (number % factor == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}