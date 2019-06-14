package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class EndTurnCommand extends Command {
    {
        name = "end turn";
        pattern = Pattern.compile("end turn", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).endTurn();
    }
}
