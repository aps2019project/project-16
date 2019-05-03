package view.commands.inGameCommands;

import view.CommandHandler;
import view.commands.Command;
import view.commands.commonCommands.ExitCommand;

import java.util.regex.Pattern;

public class CancelGameCommand extends Command {
    {
        name = "cancel game";
        pattern = Pattern.compile("cancel game", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String string = CommandHandler.scanCommandByMessage("Are you sure to cancel the game? (Y/n)");
        if (string.equalsIgnoreCase("y")) {
            new ExitCommand().doIt();
        }
    }
}
