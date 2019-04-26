package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class EndGameCommand extends Command {
    {
        name = "end game";
        pattern = Pattern.compile("end game", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController().finishTheGame();
    }
}
