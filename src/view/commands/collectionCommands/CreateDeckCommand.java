package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class CreateDeckCommand extends Command {
    {
        name = "create deck (deck name)";
        pattern = Pattern.compile("create deck (\\w+)", CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String deckName = matcher.group(1);
        CollectionController controller = new CollectionController();
        controller.createDeck(deckName);
    }
}
