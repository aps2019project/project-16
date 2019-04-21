package view.commands.shopCommands;

import controllers.ShopController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowCommand extends Command {
    {
        name = "show";
        pattern = Pattern.compile("show", Pattern.CASE_INSENSITIVE);
    }
    @Override
    public void doIt() {
        new ShopController().loadShop();
    }
}
