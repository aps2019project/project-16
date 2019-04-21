package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.MULTI_PLAYER_MENU;

public class MultiPlayerMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = MULTI_PLAYER_MENU;

        // TODO Sadegh: 4/19/19 init commands
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
