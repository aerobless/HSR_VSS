package exercise01;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(Hello.HOST, Hello.PORT);
            Hello hello = (Hello) registry.lookup(Hello.NAME);
            System.out.println(hello.getHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
