package command;

import message.Registration;

import java.util.UUID;
import java.util.function.Consumer;

/*
 * TODO: Remove the use of concrete class SimpleCommandBus
 * TODO: Define class specification
 * */
public abstract class Commands {
    public static <P extends Command> String dispatch(Class<P> type, P payload) {
        return dispatch(type, payload, UUID.randomUUID().toString());
    }

    public static <P extends Command> String dispatch(Class<P> type, P payload, String id) {
        SimpleCommandBus
                .getInstance()
                .getDispatcher()
                .dispatch(new CommandMessage(
                        id,
                        type,
                        payload));
        return id;
    }

    public static <P extends Command> Registration register(Class<P> type, Consumer<? extends CommandMessage> handler) {
        return SimpleCommandBus
                .getInstance()
                .getHandlerStore()
                .register(type, new CommandHandler(handler));
    }
}
