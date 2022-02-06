package message;

public interface MessageBroker<T, M extends Message<T>> extends Runnable {
    boolean close();

    void setSource(MessageRepository<M> repository);

    void setTarget(MessageHandlerStore<T, M> store);
}
