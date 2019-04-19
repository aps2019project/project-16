package view.commands.mainMenuCommands;

import view.commands.Command;

import java.util.regex.Pattern;

public class LogoutCommand extends Command {
    {
        name = "logout";
        pattern = Pattern.compile("logout", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 logout
    }
}
