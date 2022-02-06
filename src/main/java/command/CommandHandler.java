package command;

import message.AbstractMessageHandler;

import java.util.function.Consumer;

public class CommandHandler extends AbstractMessageHandler<Command, CommandMessage> {
    public CommandHandler(Consumer<? extends CommandMessage> handler) {
        super(handler);
    }
}
