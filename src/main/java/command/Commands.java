package command;

import java.util.UUID;
import java.util.function.Consumer;

public abstract class Commands {
    public static  <C extends Command> void dispatch(Class<C> type, C payload) {
        CommandBus
                .getInstance()
                .getDispatcher()
                .dispatch(new CommandMessage(
                        UUID.randomUUID().toString(),
                        type,
                        payload));
    }

    public static <C extends Command> void register(Class<C> type, Consumer<? extends CommandMessage> handler){
        CommandBus
                .getInstance()
                .getHandlerStore()
                .register(type, new CommandHandler(handler));
    }
}
