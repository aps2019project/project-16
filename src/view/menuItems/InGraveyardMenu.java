package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.IN_GRAVEYARD_MENU;

public class InGraveyardMenu extends MenuItem {
    @Override
    protected void initMenuItem() {
        name = IN_GRAVEYARD_MENU;

        // TODO: 4/21/19 init commands
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
