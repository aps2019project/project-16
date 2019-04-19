package view.menuItems;

import view.commands.commonCommands.*;
import view.commands.mainMenuCommands.*;

import static view.menuItems.MenuConstants.MAIN_MENU;

public class MainMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = MAIN_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new SaveCommand());
        commands.add(new LogoutCommand());
        commands.add(new HelpCommand());

        subMenus.add(new CollectionMenu());
        subMenus.add(new ShopMenu());
        subMenus.add(new BattleMenu());
    }
}
