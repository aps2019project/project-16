package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.CUSTOM_GAME_MENU;

public class CustomGameMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = CUSTOM_GAME_MENU;

        // TODO: 4/21/19
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
