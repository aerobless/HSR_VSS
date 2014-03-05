import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;


public class Ex03_HelloQueue {

	public static void main(String[] args) throws Exception {
		// Connection and Channel
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//Queue setup
		String queueName = "hello_queue";
		/*durable - true if we are declaring a durable queue (the queue will survive a server restart)*/
		boolean durable = false;
		/*exclusive - true if we are declaring an exclusive queue (restricted to this connection)*/
		boolean exclusive = false;
		/*autoDelete - true if we are declaring an autodelete queue (server will delete it when no longer in use)*/
		boolean autoDelete = false;
		/*arguments - other properties (construction arguments) for the queue*/
		Map<String, Object> props = null;
		channel.queueDeclare(queueName, durable, exclusive, autoDelete, props);
		
		//Consume messages until user aborts
		System.out.println("[*] Waiting for messages (exit crtl+c)");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		/* autoAck - true if the server should consider messages acknowledged once delivered; 
		 * false if the server should expect explicit acknowledgments */
		boolean autoAck = true;
		channel.basicConsume(queueName, autoAck, consumer);
		
		while(true){
			/*Convenience class: an implementation of Consumer with straightforward blocking semantics. */
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.printf(" [x] Received '%s'\n", message);
		}
	}
}
