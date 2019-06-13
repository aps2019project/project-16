package view.commands.shopCommands;

import controllers.ShopController;
import view.commands.Command;
import view.views.ShopView;

import java.util.regex.Pattern;

public class SellCommand extends Command {
    {
        name = "sell (card ID | Item ID)";
        pattern = Pattern.compile("sell (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int cardID = Integer.parseInt(matcher.group(1));
        new ShopController(new ShopView()).sellCard(cardID);
    }
}
