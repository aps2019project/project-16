package view.commands.collectionCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

public class SearchCommand extends Command {
    {
        name = "search (card name | item name)";
        pattern = Pattern.compile("search (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        CollectionController controller = new CollectionController();
        controller.searchCard(cardName);
    }
}
