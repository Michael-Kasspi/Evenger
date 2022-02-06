package message;

public interface MessageHandler<T, M extends Message<? extends T>> {
    void handle(M message);
}
