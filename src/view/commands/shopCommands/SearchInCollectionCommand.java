package view.commands.shopCommands;

import controllers.CollectionController;
import view.commands.Command;

import java.util.regex.Pattern;

public class SearchInCollectionCommand extends Command {
    {
        name = "search collection (card name | item name)";
        pattern = Pattern.compile("search collection (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        new CollectionController().searchCard(cardName);//may change and call ShopController
    }
}
