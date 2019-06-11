package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowOppDecks extends Command {
    {
        name = "show opponent decks";
        pattern = Pattern.compile("show opponent decks", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new CustomGameController().loadDecks();
    }
}
