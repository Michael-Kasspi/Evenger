package message;

public interface MessageDispatcher<M extends Message<?>> {
    void dispatch(M message);

    void setRepository(MessageRepository<M> repository);
}
