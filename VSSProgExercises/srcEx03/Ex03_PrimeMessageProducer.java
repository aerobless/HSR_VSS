import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Ex03_PrimeMessageProducer {
    private static final String TASK_QUEUE_NAME = "prime_queue";

    /**
     * Enter two "long"s to send a message to the prime_queue.
     *
     * @param startPrime
     * @param endPrime
     * @throws Exception
     */
    public void sendMessage(long startPrime, long endPrime) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;
        /*durable, so we don't loose messages*/
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
        
        /*Creating a new PrimeCounterMessage*/
        Ex03_PrimeCounterMessage pm = new Ex03_PrimeCounterMessage(startPrime, endPrime);

        /*Sending the PM to the queue*/
		channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, pm.toBytes()); //<--Setting to PERSISTENT_TEXT_PLAIN so we don't loose messages if mq server crashes
		System.out.println(" [x] Sent '" + pm.toString() + "'");

		/*Cleaning the environment*/
        channel.close();
        connection.close();
    }
}