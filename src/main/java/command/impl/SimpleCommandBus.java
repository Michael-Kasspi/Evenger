package command.impl;

import command.api.Command;
import command.api.CommandBus;
import message.impl.AbstractMessageBus;

public final class SimpleCommandBus extends AbstractMessageBus<Command, BasicCommandMessage> implements CommandBus {

    public SimpleCommandBus() {
        super(builder -> builder
                .withRepository(CommandInMemoryRepository::new)
                .withDispatcher(BasicCommandDispatcher::new)
                .withHandlerStore(HashMapCommandHandlerStore::new)
                .withBroker(BasicCommandBroker::new));
    }
}
