package view.commands.shopCommands;

import controllers.ShopController;
import view.commands.Command;
import view.views.ShopView;

import java.util.regex.Pattern;

public class BuyCommand extends Command {
    {
        name = "buy (card name | Item name)";
        pattern = Pattern.compile("buy (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        new ShopController(new ShopView()).buyCard(cardName);
    }
}
