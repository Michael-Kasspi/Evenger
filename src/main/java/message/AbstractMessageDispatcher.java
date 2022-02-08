package message;

public abstract class AbstractMessageDispatcher<M extends Message<?>> implements MessageDispatcher<M> {

    private MessageRepository<M> repository;

    protected AbstractMessageDispatcher(MessageRepository<M> repository) {
        this.repository = repository;
    }

    public void setRepository(MessageRepository<M> r) {
        if (repository != null) throw new IllegalStateException("repository is practically final");
        repository = r;
    }

    @Override
    public void dispatch(M message) {
        repository.add(message);
    }
}
