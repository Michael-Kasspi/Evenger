package command;

import message.AbstractRegistration;

public class CommandRegistration extends AbstractRegistration {

    public CommandRegistration(Runnable callback) {
        super(callback);
    }
}
