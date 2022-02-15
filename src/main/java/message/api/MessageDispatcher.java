package message.api;

@FunctionalInterface
public interface MessageDispatcher<M extends Message<?>> {
    void dispatch(M message);
}
