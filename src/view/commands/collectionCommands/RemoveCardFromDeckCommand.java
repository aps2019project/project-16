package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;
import view.views.CollectionView;

import java.util.regex.Pattern;

public class RemoveCardFromDeckCommand extends Command {
    {
        name = "remove (card ID | hero ID) from deck (deck name)";
        pattern = Pattern.compile("remove (\\d+) from deck (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int cardID = Integer.parseInt(matcher.group(1));
        String deckName = matcher.group(2);
        CollectionController controller = new CollectionController(new CollectionView());
        controller.removeCardFromDeck(cardID, deckName);
    }
}
