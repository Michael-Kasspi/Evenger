package commands;

import command.Command;

public class EmptyTestCommand implements Command {

    public EmptyTestCommand() {
        System.out.println("Created empty test command");
    }

    @Override
    public String getId() {
        return "Empty test command";
    }
}
