package command;

import message.MessageHandler;
import message.MessageHandlerStore;
import message.Registration;

import java.util.HashMap;
import java.util.Map;

public class CommandHandlerStore implements MessageHandlerStore<Command, CommandMessage> {

    private static final Map<Class<? extends Command>, MessageHandler<Command, CommandMessage>> HANDLERS_MAP = new HashMap<>();

    @Override
    public Registration register(Class<? extends Command> type, MessageHandler<Command, CommandMessage> handler) {
        if (HANDLERS_MAP.containsKey(type)) throw new MultipleCommandHandlersException(type);
        HANDLERS_MAP.put(type, handler);
        return new CommandRegistration(() -> HANDLERS_MAP.remove(type));
    }

    @Override
    public void handle(CommandMessage message) {
        final Class<? extends Command> type = message.getType();
        if (!HANDLERS_MAP.containsKey(type)) {
            throw new CommandHandlerNotFoundException(type);
        }
        HANDLERS_MAP.get(type).handle(message);
    }
}
