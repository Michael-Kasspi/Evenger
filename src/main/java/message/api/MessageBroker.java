package message.api;

public interface MessageBroker extends Runnable {
    boolean close();
}
