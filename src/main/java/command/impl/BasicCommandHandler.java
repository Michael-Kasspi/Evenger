package command.impl;

import command.api.Command;
import command.api.CommandHandler;
import message.impl.AbstractMessageHandler;

import java.util.function.Consumer;

public class BasicCommandHandler extends AbstractMessageHandler<Command, BasicCommandMessage> implements CommandHandler {
    public BasicCommandHandler(Consumer<? extends BasicCommandMessage> handler) {
        super(handler);
    }
}
