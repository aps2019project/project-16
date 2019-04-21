package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class AddCardToDeckCommand extends Command {
    {
        name = "add (card ID | hero ID) to deck (deck name)";
        pattern = Pattern.compile("add (\\d+) to deck (\\w+)", CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int cardID = Integer.parseInt(matcher.group(1));
        String deckName = matcher.group(2);
        CollectionController controller = new CollectionController();
        controller.addCardToDeck(cardID, deckName);
    }
}
