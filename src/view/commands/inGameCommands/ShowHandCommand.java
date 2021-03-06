package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class ShowHandCommand extends Command {
    {
        name = "show hand";
        pattern = Pattern.compile("show hand", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).loadHand();
    }
}
