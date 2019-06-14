package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;
import view.views.CollectionView;

import java.util.regex.Pattern;

public class SelectDeckCommand extends Command {
    {
        name = "select deck (deck name)";
        pattern = Pattern.compile("select deck (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String deckName = matcher.group(1);
        CollectionController controller = new CollectionController(new CollectionView());
        controller.selectDeck(deckName);
    }
}
