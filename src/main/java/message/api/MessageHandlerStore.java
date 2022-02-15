package message.api;

public interface MessageHandlerStore<P, M extends Message<P>, H extends MessageHandler<P, M>> {
    Registration register(Class<? extends P> type, H handler);

    void handle(M message);
}
