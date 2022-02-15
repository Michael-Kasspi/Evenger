package command.impl;

import command.api.CommandDispatcher;
import message.impl.AbstractMessageDispatcher;
import message.api.MessageRepository;

public class BasicCommandDispatcher extends AbstractMessageDispatcher<BasicCommandMessage> implements CommandDispatcher {

    public BasicCommandDispatcher(MessageRepository<BasicCommandMessage> repository) {
        super(repository);
    }
}
