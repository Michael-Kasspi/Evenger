package message;

import java.util.function.*;

public abstract class AbstractMessageBus<P, M extends Message<P>> implements MessageBus<P, M> {

    private static final String INACTIVE_MESSAGE = "This message bus is inactive";
    private static final String ACTIVE_MESSAGE = " This message bus already active";

    protected final MessageBroker broker;
    protected final MessageDispatcher<M> dispatcher;
    protected final MessageHandlerStore<P, M> store;
    protected final MessageRepository<M> repository;

    private boolean active;

    protected AbstractMessageBus(UnaryOperator<Builder> builderFunction) {
        Builder builder = builderFunction.apply(new Builder());
        if (!builder.validate()) throw new IllegalArgumentException("Not all dependencies are satisfied");
        repository = builder.repository.get();
        dispatcher = builder.dispatcher.apply(repository);
        store = builder.store.get();
        broker = builder.broker.apply(repository, store);
    }

    @Override
    public synchronized void init() {
        checkActive(active, ACTIVE_MESSAGE);
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
    public MessageHandlerStore<P, M> getHandlerStore() {
        checkActive(!active, INACTIVE_MESSAGE);
        return store;
    }

    private void checkActive(boolean condition, String message) {
        if (condition) throw new IllegalStateException(message);
    }

    public class Builder {
        Supplier<MessageRepository<M>> repository;
        Function<MessageRepository<M>, MessageDispatcher<M>> dispatcher;
        BiFunction<MessageRepository<M>, MessageHandlerStore<P, M>, MessageBroker> broker;
        Supplier<MessageHandlerStore<P, M>> store;

        public Builder withRepository(Supplier<MessageRepository<M>> repository) {
            this.repository = repository;
            return this;
        }

        public Builder withDispatcher(Function<MessageRepository<M>, MessageDispatcher<M>> dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Builder withHandlerStore(Supplier<MessageHandlerStore<P, M>> store) {
            this.store = store;
            return this;
        }

        public Builder withBroker(BiFunction<MessageRepository<M>, MessageHandlerStore<P, M>, MessageBroker> broker) {
            this.broker = broker;
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
