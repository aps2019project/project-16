package view.menuItems;

import view.commands.commonCommands.*;

import static view.menuItems.MenuConstants.BATTLE_MENU;

public class BattleMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = BATTLE_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());
        commands.add(new ShowMenuCommand());

        subMenus.add(new SinglePlayerMenu());
        subMenus.add(new MultiPlayerMenu());
    }
}
