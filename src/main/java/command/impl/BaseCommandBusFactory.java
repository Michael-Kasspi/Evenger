package command.impl;

import command.api.CommandBus;
import command.api.CommandBusFactory;

public class BaseCommandBusFactory implements CommandBusFactory {
    @Override
    public CommandBus get(String type) {
        if ("Simple".equalsIgnoreCase(type)) {
            return new SimpleCommandBus();
        } else return null;
    }
}
