package command.api;

import command.impl.BasicCommandMessage;
import message.api.MessageHandlerStore;
import message.api.Registration;

public interface CommandHandlerStore extends MessageHandlerStore<Command, BasicCommandMessage, CommandHandler> {
    @Override
    Registration register(Class<? extends Command> type, CommandHandler handler);

    @Override
    void handle(BasicCommandMessage message);
}
