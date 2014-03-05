import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_TwitterMessageConsumer {
    private static final String EXCHANGE_NAME = "twitter";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*Declare exchange, we can see the exchange with "$ rabbitmqctl list_exchanges"*/
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        /*Creating a nameless queue, we can see those with "$ rabbitmqctl list_bindings"*/
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");
        
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
 
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.format(" [x] Received '%s'\n", message);
        }
    }
}