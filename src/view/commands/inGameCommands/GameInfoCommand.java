package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class GameInfoCommand extends Command {
    {
        name = "game info";
        pattern = Pattern.compile("game info", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).loadGameInfo();
    }
}
