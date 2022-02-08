package message;

public interface MessageDispatcher<M extends Message<?>> {
    void dispatch(M message);
}
