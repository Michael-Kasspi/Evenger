package command;

import message.AbstractMessageDispatcher;
import message.MessageRepository;

public class CommandDispatcher extends AbstractMessageDispatcher<CommandMessage> {

    public CommandDispatcher(MessageRepository<CommandMessage> repository) {
        super(repository);
    }

    public CommandDispatcher() {
    }
}
