package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowTableCommand extends Command {
    {
        name = "show table";
        pattern = Pattern.compile("show table", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController().loadGameTable();
    }
}
