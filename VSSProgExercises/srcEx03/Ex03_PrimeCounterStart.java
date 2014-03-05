
public class Ex03_PrimeCounterStart {
	public static void main(String[] args) throws Exception {
		Ex03_PrimeMessageProducer producer = new Ex03_PrimeMessageProducer();
		producer.sendMessage(1, 100000);
		producer.sendMessage(100001, 200000);
		producer.sendMessage(200001, 300000);
		producer.sendMessage(300001, 400000);
	}
}
