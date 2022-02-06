package command;

public class CommandHandlerNotFoundException extends RuntimeException {
    private final Class<? extends Command> type;

    public <T extends Command> CommandHandlerNotFoundException(Class<T> type) {

        this.type = type;
    }

    public Class<? extends Command> getType() {
        return type;
    }
}
