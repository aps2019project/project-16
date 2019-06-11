package view.commands.shopCommands;

import controllers.ShopController;
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
        new ShopController().searchInShop(cardName);
    }
}
