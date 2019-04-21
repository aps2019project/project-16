package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ValidateDeckCommand extends Command {
    {
        name = "validate deck (deck name)";
        pattern = Pattern.compile("validate deck (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String deckName = matcher.group(1);
        CollectionController controller = new CollectionController();
        controller.validateDeck(deckName);
    }
}
