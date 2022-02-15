package message.impl;

import message.api.Message;

public abstract class AbstractMessage<T> implements Message<T> {

    private final T payload;
    private final Class<? extends T> type;
    private final String id;

    protected AbstractMessage(String id, Class<? extends T> type, T payload) {
        this.id = id;
        this.type = type;
        this.payload = payload;
    }

    @Override
    public T getPayload() {
        return payload;
    }

    @Override
    public Class<? extends T> getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}
