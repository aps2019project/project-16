package view.menuItems;

import view.commands.commonCommands.*;
import view.commands.shopCommands.*;

import static view.menuItems.MenuConstants.SHOP_MENU;

public class ShopMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = SHOP_MENU;

        commands.add(new ShowCollectionCommand());
        commands.add(new SearchCommand());
        commands.add(new SearchInCollectionCommand());
        commands.add(new BuyCommand());
        commands.add(new SellCommand());
        commands.add(new ShowCommand());

        commands.add(new ExitCommand());
        commands.add(new HelpCommand());
        //no subMenu
    }
}
