
package message;

public interface MessageBus<T, M extends Message<T>> {
    void start();

    void close();

    boolean isActive();

    MessageDispatcher<M> getDispatcher();

    MessageHandlerStore<T, M> getHandlerStore();
}