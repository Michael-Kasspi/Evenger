package command.impl;

import command.api.Command;
import command.api.CommandMessage;
import message.impl.AbstractMessage;

public class BasicCommandMessage extends AbstractMessage<Command> implements CommandMessage {
    public BasicCommandMessage(String id, Class<? extends Command> type, Command payload) {
        super(id, type, payload);
    }
}
