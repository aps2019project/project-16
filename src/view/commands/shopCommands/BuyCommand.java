package view.commands.shopCommands;

import controllers.ShopController;
import view.commands.Command;

import java.util.regex.Pattern;

public class BuyCommand extends Command {
    {
        name = "buy (card ID | Item ID)";
        pattern = Pattern.compile("buy (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int cardID = Integer.parseInt(matcher.group(1));
        new ShopController().buyCard(cardID);
    }
}
