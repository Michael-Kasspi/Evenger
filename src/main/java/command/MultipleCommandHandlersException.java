package command;

public class MultipleCommandHandlersException extends RuntimeException {
    private final Class<? extends Command> command;

    public <T extends Command> MultipleCommandHandlersException(Class<T> command) {
        this.command = command;
    }

    public Class<? extends Command> getCommand() {
        return command;
    }
}
