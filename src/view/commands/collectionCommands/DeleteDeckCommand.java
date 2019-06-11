package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class DeleteDeckCommand extends Command {
    {
        name = "delete deck (deck name)";
        pattern = Pattern.compile("delete deck (\\w+( \\w+)*)", CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String deckName = matcher.group(1);
        CollectionController controller = new CollectionController();
        controller.deleteDeck(deckName);
    }
}
