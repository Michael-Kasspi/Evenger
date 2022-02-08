package message;

public interface MessageBroker extends Runnable {
    boolean close();
}
