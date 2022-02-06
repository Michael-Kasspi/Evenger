package message;

public abstract class AbstractMessageBroker<T, M extends Message<T>> implements MessageBroker<T, M> {

    protected boolean processing = true;
    protected MessageRepository<M> source;
    protected MessageHandlerStore<T, M> target;

    protected AbstractMessageBroker(MessageRepository<M> source, MessageHandlerStore<T, M> target) {
        this.source = source;
        this.target = target;
    }

    protected AbstractMessageBroker() {
    }

    @Override
    public void setSource(MessageRepository<M> repository) {
        assertSetOnce(source);
        source = repository;
    }

    @Override
    public void setTarget(MessageHandlerStore<T, M> handlerStore) {
        assertSetOnce(target);
        target = handlerStore;
    }

    @Override
    public boolean close() {
        processing = false;
        return true;
    }

    @Override
    public void run() {
        if (source == null || target == null) throw new IllegalStateException("Not all dependencies are satisfied");
        while (processing) {
            target.handle(source.next());
        }
    }

    private void assertSetOnce(Object object) {
        if (object != null) throw new IllegalStateException("This property is already set");
    }
}
