
package message.api;

public interface MessageBus<T, M extends Message<T>> extends AutoCloseable {
    void init();

    void close();

    boolean isActive();

    MessageDispatcher<M> getDispatcher();

    MessageHandlerStore<T, M> getHandlerStore();
}