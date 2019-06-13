package view.commands.shopCommands;

import controllers.ShopController;
import view.commands.Command;
import view.views.ShopView;

import java.util.regex.Pattern;

public class SearchInCollectionCommand extends Command {
    {
        name = "collection search (card name | item name)";
        pattern = Pattern.compile("collection search (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        new ShopController(new ShopView()).searchInCollection(cardName);
    }
}
