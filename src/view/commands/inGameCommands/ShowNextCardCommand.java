package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class ShowNextCardCommand extends Command {
    {
        name = "show next card";
        pattern = Pattern.compile("show next card", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).loadNextCard();
    }
}
