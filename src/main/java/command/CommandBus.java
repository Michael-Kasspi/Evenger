package command;

import message.MessageBus;

public interface CommandBus extends MessageBus<Command, CommandMessage> {
}
