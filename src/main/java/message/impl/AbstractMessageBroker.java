package message.impl;

import message.api.Message;
import message.api.MessageBroker;
import message.api.MessageHandlerStore;
import message.api.MessageRepository;

public abstract class AbstractMessageBroker<T, M extends Message<T>> implements MessageBroker {

    protected boolean processing = true;
    protected final MessageRepository<M> source;
    protected final MessageHandlerStore<T, M> target;

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
