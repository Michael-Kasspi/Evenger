package command;

import message.*;

public final class SimpleCommandBus extends AbstractMessageBus<Command, CommandMessage> implements CommandBus {

    public SimpleCommandBus() {
        super(builder -> builder
                .withRepository(CommandInMemoryRepository::new)
                .withDispatcher(CommandDispatcher::new)
                .withHandlerStore(CommandHandlerStore::new)
                .withBroker(CommandBroker::new));
    }
}
