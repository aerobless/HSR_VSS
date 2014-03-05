import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_PrimeMessageProducer {
    private static final String TASK_QUEUE_NAME = "prime_queue";

    public void sendMessage(long startPrime, long endPrime) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        //TODO Ex03_PrimeCounterMessage erstellen mit dem Zahlenbereich aus argv[0]-argv[1]
        //TODO und die Message publizieren
        
        Ex03_PrimeCounterMessage pm = new Ex03_PrimeCounterMessage(startPrime, endPrime);
		channel.basicPublish("", TASK_QUEUE_NAME, null, pm.toBytes());
		System.out.println(" [x] Sent '" + pm.toString() + "'");

        channel.close();
        connection.close();
    }
}