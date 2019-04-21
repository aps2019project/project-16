package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowDeckCommand extends Command {
    {
        name = "show deck (deck name)";
        pattern = Pattern.compile("show deck (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String deckName = matcher.group(1);
        new CollectionController().loadDeck(deckName);
    }
}
