package command;

import message.AbstractMessage;

public class CommandMessage extends AbstractMessage<Command> {
    public CommandMessage(String id, Class<? extends Command> type, Command payload) {
        super(id, type, payload);
    }
}
