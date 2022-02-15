package command.impl;

import command.api.Command;
import command.api.CommandBroker;
import command.api.CommandMessage;
import message.api.MessageHandler;
import message.api.MessageHandlerStore;
import message.api.MessageRepository;
import message.impl.AbstractMessageBroker;

public class BasicCommandBroker extends AbstractMessageBroker<Command, CommandMessage> implements CommandBroker {
    public BasicCommandBroker(MessageRepository<CommandMessage> source, MessageHandlerStore<Command, CommandMessage, MessageHandler<Command, CommandMessage>> target) {
        super(source, target);
    }
}
