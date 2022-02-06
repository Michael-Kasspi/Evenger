package command;

import message.*;
public class CommandBus extends AbstractMessageBus<Command ,CommandMessage> {

    private static volatile CommandBus instance;
    private static final Object MUTEX = new Object();

    private CommandBus() {
        super(new Builder<Command, CommandMessage>()
                .withRepository(new CommandInMemoryRepository())
                .withDispatcher(new CommandDispatcher())
                .withHandlerStore(new CommandHandlerStore())
                .withBroker(new CommandBroker()));
    }

    public static CommandBus getInstance() {
        CommandBus local = instance;
        if (null == local) {
            synchronized (MUTEX) {
                local = instance;
                if (null == local) {
                    instance = local = new CommandBus();
                }
            }
        }
        return local;
    }

    @Override
    public synchronized void close() {
        super.close();
        instance = null;
    }
}
