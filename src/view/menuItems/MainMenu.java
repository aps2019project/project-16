package view.menuItems;

import view.commands.commonCommands.EnterMenuCommand;
import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.commonCommands.ShowMenuCommand;
import view.commands.mainMenuCommands.LogoutCommand;
import view.commands.mainMenuCommands.SaveCommand;

import static view.menuItems.MenuConstants.MAIN_MENU;

public class MainMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = MAIN_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new SaveCommand());
        commands.add(new LogoutCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());
        commands.add(new ShowMenuCommand());

        subMenus.add(new CollectionMenu());
        subMenus.add(new ShopMenu());
        subMenus.add(new BattleMenu());
    }
}
