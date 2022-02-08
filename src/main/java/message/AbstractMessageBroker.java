package message;

public abstract class AbstractMessageBroker<T, M extends Message<T>> implements MessageBroker<T, M> {

    protected boolean processing = true;
    protected MessageRepository<M> source;
    protected MessageHandlerStore<T, M> target;

    protected AbstractMessageBroker(MessageRepository<M> source, MessageHandlerStore<T, M> target) {
        this.source = source;
        this.target = target;
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
}
