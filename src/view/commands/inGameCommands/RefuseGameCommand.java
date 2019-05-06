package view.commands.inGameCommands;

import controllers.InGameController;
import view.CommandHandler;
import view.commands.Command;

import java.util.regex.Pattern;

public class RefuseGameCommand extends Command {
    {
        name = "refuse game";
        pattern = Pattern.compile("refuse game", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String string = CommandHandler.scanCommandByMessage("Are you sure to refuse from the game? (Y/n)");
        if (string != null && string.equalsIgnoreCase("y")) {
            new InGameController().refuseGame();
        }
    }
}
