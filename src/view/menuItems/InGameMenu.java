package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class InGameMenu extends MenuItem {
    @Override
    protected void initMenuItem() {
        name = IN_GAME_MENU;

        // TODO: 4/21/19 init commands
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new InGraveyardMenu());
    }
}
