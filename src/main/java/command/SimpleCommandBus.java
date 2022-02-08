package command;

import message.*;

public final class SimpleCommandBus extends AbstractMessageBus<Command, CommandMessage> implements CommandBus {

    private static volatile SimpleCommandBus instance;
    private static final Object MUTEX = new Object();

    private SimpleCommandBus() {
        super(builder -> builder
                .withRepository(CommandInMemoryRepository::new)
                .withDispatcher(CommandDispatcher::new)
                .withHandlerStore(CommandHandlerStore::new)
                .withBroker(CommandBroker::new));
    }

    public static SimpleCommandBus getInstance() {
        SimpleCommandBus local = instance;
        if (null == local) {
            synchronized (MUTEX) {
                local = instance;
                if (null == local) {
                    instance = local = new SimpleCommandBus();
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
