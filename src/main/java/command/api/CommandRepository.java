package command.api;

import command.impl.BasicCommandMessage;
import message.api.MessageRepository;

public interface CommandRepository extends MessageRepository<BasicCommandMessage> {
}
