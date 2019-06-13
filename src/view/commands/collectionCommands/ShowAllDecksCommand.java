package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;
import view.views.CollectionView;

import java.util.regex.Pattern;

public class ShowAllDecksCommand extends Command {
    {
        name = "show all decks";
        pattern = Pattern.compile("show all decks", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new CollectionController(new CollectionView()).loadAllDecks();
    }
}
