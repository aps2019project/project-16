package view.commands.multiPlayerCommands;

import controllers.MultiPlayerController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowSecondUser extends Command {
    {
        name = "show second user";
        pattern = Pattern.compile("show second user", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new MultiPlayerController().loadSecondAccount();
    }
}
