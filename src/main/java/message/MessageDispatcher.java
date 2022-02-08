package message;

@FunctionalInterface
public interface MessageDispatcher<M extends Message<?>> {
    void dispatch(M message);
}
