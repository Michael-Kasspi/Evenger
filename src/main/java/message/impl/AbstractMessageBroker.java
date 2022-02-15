package message.impl;

import message.api.*;

public abstract class AbstractMessageBroker<P, M extends Message<P>> implements MessageBroker {

    protected boolean processing = true;
    protected final MessageRepository<M> source;
    protected final MessageHandlerStore<P,M,MessageHandler<P,M>> target;

    protected AbstractMessageBroker(MessageRepository<M> source, MessageHandlerStore<P, M, MessageHandler<P,M>> target) {
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
