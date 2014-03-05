import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_TwitterMessageTopicProducer {
    private static final String EXCHANGE_NAME = "twitter_topics";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //TODO Deklariere exchange

        //TODO Publiziere message von argv

        channel.close();
        connection.close();
    }

    private static String getTopic(String[] argv) {
        if (argv.length < 1)
            return "boris.wimbledon";
        return argv[0];
    }

    private static String getMessage(String[] argv) {
        if (argv.length < 2)
            return "What a tough match!";
        return argv[1];
    }
}