package command;

import message.AbstractMessageBroker;
import message.MessageHandlerStore;
import message.MessageRepository;

public class CommandBroker extends AbstractMessageBroker<Command ,CommandMessage> {
    public CommandBroker(MessageRepository<CommandMessage> source, MessageHandlerStore<Command, CommandMessage> target) {
        super(source, target);
    }
}
