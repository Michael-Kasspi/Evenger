package message;

public interface MessageHandlerStore<T, M extends Message<T>> {
    Registration register(Class<? extends T> type, MessageHandler<T, M> handler);

    void handle(M message);
}
