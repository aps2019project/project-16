package view.menuItems;

import view.commands.EnterMenuCommand;
import view.commands.ExitCommand;
import view.commands.HelpCommand;

import static view.menuItems.MenuConstants.BATTLE_MENU;

public class BattleMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = BATTLE_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new SinglePlayerMenu());
        subMenus.add(new MultiPlayerMenu());
    }
}
