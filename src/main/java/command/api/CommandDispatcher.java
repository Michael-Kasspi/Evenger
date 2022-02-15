package command.api;

import command.impl.BasicCommandMessage;
import message.api.MessageDispatcher;

public interface CommandDispatcher extends MessageDispatcher<BasicCommandMessage> {
}
