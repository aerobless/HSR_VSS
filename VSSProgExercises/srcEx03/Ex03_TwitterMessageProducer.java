import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_TwitterMessageProducer {
    private static final String EXCHANGE_NAME = "twitter";

    public static void main(String[] argv) throws Exception {
    	ArrayList<String> meaninglessChatter = new ArrayList<String>();
    	meaninglessChatter.add("Never say 'OOPS!' always say 'Ah, Interesting!'");
    	meaninglessChatter.add("If you try and don't succeed, cheat. Repeat until caught. Then lie.");
    	meaninglessChatter.add("If debugging is the process of removing software bugs, then programming must be the process of putting them in.");
    	meaninglessChatter.add("Before software can be reusable it first has to be usable.");
    	meaninglessChatter.add("A SQL query goes into a bar, walks up to two tables and asks, 'Can I join you?'");
    	meaninglessChatter.add("To understand what recursion is, you must first understand recursion.");
    	meaninglessChatter.add("Commandline russian roulette: [ $[ $RANDOM % 6 ] == 0 ] && rm -rf / || echo *Click*");
    	System.out.println("Starting to spam meaningless chatter...");
    	while(true){
    		Random rn = new Random();
    		int randomChoice = rn.nextInt(meaninglessChatter.size()-1);
        	sendMessage(meaninglessChatter.get(randomChoice));
        	Thread.sleep(5000);
    	}
    }
    
    public static void sendMessage(String message) throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /*Declare exchange*/
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        /*Publish twitter message*/
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        channel.close();
        connection.close();
    }
}