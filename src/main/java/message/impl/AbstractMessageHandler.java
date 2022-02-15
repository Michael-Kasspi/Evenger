package message.impl;

import message.api.Message;
import message.api.MessageHandler;

import java.util.function.Consumer;

public abstract class AbstractMessageHandler<T, M extends Message<? extends T>> implements MessageHandler<T, M> {

    protected final Consumer<M> handler;

    protected AbstractMessageHandler(Consumer<? extends M> handler) {
        this.handler = (Consumer<M>) handler;
    }

    @Override
    public void handle(M message) {
        handler.accept(message);
    }
}
