package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.SHOP_MENU;

public class ShopMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = SHOP_MENU;

        // TODO: 4/21/19
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
