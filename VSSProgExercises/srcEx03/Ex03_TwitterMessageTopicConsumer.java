import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_TwitterMessageTopicConsumer {
    private static final String EXCHANGE_NAME = "twitter_topics";

    public static void main(String[] argv) throws Exception {
    	   ConnectionFactory factory = new ConnectionFactory();
           factory.setHost("localhost");
           Connection connection = factory.newConnection();
           Channel channel = connection.createChannel();

           /*Declare exchange, we can see the exchange with "$ rabbitmqctl list_exchanges"
            *IMPORTANT: Had to change to "direct" in the TOPICs version. Because "fanout"
            *is only usefull for mindless spamming!*/
           channel.exchangeDeclare(EXCHANGE_NAME, "direct"); 

           /*Creating a nameless queue, we can see those with "$ rabbitmqctl list_bindings"*/
           String queueName = channel.queueDeclare().getQueue();
           
           /*Binding only those topics (routing-key) that we're interested in*/
           String[] interestingTopics = {"programming.humour", "programming.serious","foo.bar.*"};
  
           for(String topic : interestingTopics){    
        	   channel.queueBind(queueName, EXCHANGE_NAME, topic);
        	 }

           System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");
           
           QueueingConsumer consumer = new QueueingConsumer(channel);
           channel.basicConsume(queueName, true, consumer);
    
           while (true) {
               QueueingConsumer.Delivery delivery = consumer.nextDelivery();
               String message = new String(delivery.getBody());
               String routingKey = delivery.getEnvelope().getRoutingKey();
               System.out.println(" [x] Received: "+message+" with keywords: "+routingKey);
           }  
    }
}