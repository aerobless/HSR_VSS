import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_HelloQueueProducer {

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
		
		String message = "Hello World!";
		channel.basicPublish("", queueName, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
	    connection.close();
	}
}
