package message.impl;

import message.api.Registration;

public abstract class AbstractRegistration implements Registration {

    protected final Runnable callback;

    protected AbstractRegistration(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public void unregister() {
        callback.run();
    }
}
