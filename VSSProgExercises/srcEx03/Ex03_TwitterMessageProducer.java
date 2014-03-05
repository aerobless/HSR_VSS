import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_TwitterMessageProducer {
    private static final String EXCHANGE_NAME = "twitter";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //TODO Deklariere exchange

        //TODO Publiziere Nachricht von argv

        channel.close();
        connection.close();
    }
}