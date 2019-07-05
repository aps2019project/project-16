package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.graveyardCommands.ShowCardsCommand;
import view.commands.graveyardCommands.ShowInfoCommand;

import static view.menuItems.MenuConstants.IN_GRAVEYARD_MENU;

public class GraveyardMenu extends MenuItem {
    @Override
    protected void initMenuItem() {
        name = IN_GRAVEYARD_MENU;

        commands.add(new ShowInfoCommand());
        commands.add(new ShowCardsCommand());

        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
