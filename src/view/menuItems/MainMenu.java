package view.menuItems;

import view.commands.EnterMenuCommand;
import view.commands.ExitCommand;
import view.commands.HelpCommand;

import static view.menuItems.MenuConstants.MAIN_MENU;

public class MainMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = MAIN_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new CollectionMenu());
        subMenus.add(new ShopMenu());
        subMenus.add(new BattleMenu());
    }
}
