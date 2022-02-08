package command;

import message.MessageBusFactory;

public class CommandBusFactory implements MessageBusFactory<CommandBus> {
    @Override
    public CommandBus get(String type) {
        if ("Simple".equalsIgnoreCase(type)) {
            return new SimpleCommandBus();
        } else return null;
    }
}
