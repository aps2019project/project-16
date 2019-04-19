package view.commands.commonCommands;

import view.commands.Command;

import java.util.regex.Pattern;

public class ExitCommand extends Command {
    {
        name = "exit";
        pattern = Pattern.compile("exit", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 goto parentMenu if != null
    }
}
