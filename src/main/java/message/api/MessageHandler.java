package message.api;

@FunctionalInterface
public interface MessageHandler<P, M extends Message<? extends P>> {
    void handle(M message);
}
