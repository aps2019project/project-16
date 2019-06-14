package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;
import view.views.CustomGameView;

import java.util.regex.Pattern;

public class ShowOppDecksCommand extends Command {
    {
        name = "show opponent decks";
        pattern = Pattern.compile("show opponent decks", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new CustomGameController(new CustomGameView()).loadDecks();
    }
}
