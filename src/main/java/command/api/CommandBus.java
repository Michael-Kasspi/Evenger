package command.api;

import command.impl.BasicCommandMessage;
import message.api.MessageBus;

public interface CommandBus extends MessageBus<Command, BasicCommandMessage> {
}
