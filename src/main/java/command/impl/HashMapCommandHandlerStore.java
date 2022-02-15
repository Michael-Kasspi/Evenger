package command.impl;

import command.api.Command;
import command.api.CommandHandler;
import command.api.CommandHandlerStore;
import command.ex.CommandHandlerNotFoundException;
import command.ex.MultipleCommandHandlersException;
import message.api.Registration;

import java.util.HashMap;
import java.util.Map;

public class HashMapCommandHandlerStore implements CommandHandlerStore {

    private static final Map<Class<? extends Command>, CommandHandler> HANDLERS_MAP = new HashMap<>();

    @Override
    public Registration register(Class<? extends Command> type, CommandHandler handler) {
        if (HANDLERS_MAP.containsKey(type)) throw new MultipleCommandHandlersException(type);
        HANDLERS_MAP.put(type, handler);
        return new BasicCommandRegistration(() -> HANDLERS_MAP.remove(type));
    }

    @Override
    public void handle(BasicCommandMessage message) {
        final Class<? extends Command> type = message.getType();
        if (!HANDLERS_MAP.containsKey(type)) {
            throw new CommandHandlerNotFoundException(type);
        }
        HANDLERS_MAP.get(type).handle(message);
    }
}
