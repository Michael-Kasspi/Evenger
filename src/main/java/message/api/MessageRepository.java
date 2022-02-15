package message.api;

public interface MessageRepository<M extends Message<?>> {
    boolean add(M message);

    M next();

    M peek();
}
