package view.commands.mainMenuCommands;

import view.commands.Command;
import view.commands.commonCommands.ExitCommand;

import java.util.regex.Pattern;

public class LogoutCommand extends Command {
    {
        name = "logout";
        pattern = Pattern.compile("logout", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new ExitCommand().doIt();
    }
}
