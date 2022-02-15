package command.impl;

import command.api.Command;
import command.api.CommandBroker;
import command.api.CommandHandlerStore;
import message.api.MessageRepository;
import message.impl.AbstractMessageBroker;

public class BasicCommandBroker extends AbstractMessageBroker<Command, BasicCommandMessage> implements CommandBroker {
    public BasicCommandBroker(MessageRepository<BasicCommandMessage> source, CommandHandlerStore target) {
        super(source, target);
    }
}
