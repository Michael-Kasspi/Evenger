import command.api.Command;

public class EmptyTestCommand implements Command {

    @Override
    public String getId() {
        return "";
    }
}
