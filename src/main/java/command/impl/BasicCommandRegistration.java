package command.impl;

import command.api.CommandRegistration;
import message.impl.AbstractRegistration;

public class BasicCommandRegistration extends AbstractRegistration implements CommandRegistration {

    public BasicCommandRegistration(Runnable callback) {
        super(callback);
    }
}
