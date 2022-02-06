package command;

import message.Registration;

import java.util.UUID;
import java.util.function.Consumer;

public abstract class Commands {
    public static  <C extends Command> String dispatch(Class<C> type, C payload) {
        return dispatch(type, payload, UUID.randomUUID().toString());
    }

    public static  <C extends Command> String dispatch(Class<C> type, C payload, String id) {
        CommandBus
                .getInstance()
                .getDispatcher()
                .dispatch(new CommandMessage(
                        id,
                        type,
                        payload));
        return id;
    }

    public static <C extends Command> Registration register(Class<C> type, Consumer<? extends CommandMessage> handler){
        return CommandBus
                .getInstance()
                .getHandlerStore()
                .register(type, new CommandHandler(handler));
    }
}
