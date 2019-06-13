package view.commands.shopCommands;

import controllers.CollectionController;
import controllers.ShopController;
import view.commands.Command;
import view.views.ShopView;

import java.util.regex.Pattern;

public class ShowCollectionCommand extends Command {
    {
        name = "show collection";
        pattern = Pattern.compile("show collection", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new ShopController(new ShopView()).loadCollection();
    }
}
