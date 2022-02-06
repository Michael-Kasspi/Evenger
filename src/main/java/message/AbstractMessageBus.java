package message;

public abstract class AbstractMessageBus<T, M extends Message<T>> implements MessageBus<T, M> {

    private static final String INACTIVE_MESSAGE = "This message bus is inactive";
    private static final String ACTIVE_MESSAGE = " This message bus already active";

    protected final MessageBroker<T, M> broker;
    protected final MessageDispatcher<M> dispatcher;
    protected final MessageHandlerStore<T, M> store;
    protected final MessageRepository<M> repository;

    private boolean active;

    protected AbstractMessageBus(Builder<T, M> builder) {
        if (!builder.validate()) throw new IllegalArgumentException("Not all dependencies are satisfied");
        repository = builder.repository;
        dispatcher = builder.dispatcher;
        broker = builder.broker;
        store = builder.store;
    }

    @Override
    public synchronized void start() {
        checkActive(active, ACTIVE_MESSAGE);
        dispatcher.setRepository(repository);
        broker.setSource(repository);
        broker.setTarget(store);
        new Thread(broker).start();
        active = true;
    }

    @Override
    public synchronized void close() {
        checkActive(!active, INACTIVE_MESSAGE);
        active = false;
        broker.close();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public MessageDispatcher<M> getDispatcher() {
        checkActive(!active, INACTIVE_MESSAGE);
        return dispatcher;
    }

    @Override
    public MessageHandlerStore<T, M> getHandlerStore() {
        checkActive(!active, INACTIVE_MESSAGE);
        return store;
    }

    private void checkActive(boolean condition, String message) {
        if (condition) throw new IllegalStateException(message);
    }

    public static class Builder<T, M extends Message<T>> {
        MessageRepository<M> repository;
        MessageDispatcher<M> dispatcher;
        MessageBroker<T, M> broker;
        MessageHandlerStore<T, M> store;

        public Builder<T, M> withRepository(MessageRepository<M> repository) {
            this.repository = repository;
            return this;
        }

        public Builder<T, M> withDispatcher(MessageDispatcher<M> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Builder<T, M> withBroker(MessageBroker<T, M> broker) {
            this.broker = broker;
            return this;
        }

        public Builder<T, M> withHandlerStore(MessageHandlerStore<T, M> store) {
            this.store = store;
            return this;
        }

        public boolean validate() {
            return repository != null &&
                    dispatcher != null &&
                    broker != null &&
                    store != null;
        }
    }
}
