package message;

public interface MessageBroker<T, M extends Message<T>> extends Runnable {
public interface MessageBroker extends Runnable {
    boolean close();
}
