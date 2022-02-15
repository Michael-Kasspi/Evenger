package command.api;

import command.impl.BasicCommandMessage;
import message.api.MessageHandler;

public interface CommandHandler extends MessageHandler<Command, BasicCommandMessage> {
}
