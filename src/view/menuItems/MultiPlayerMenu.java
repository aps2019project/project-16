package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.multiPlayerCommands.*;

import static view.menuItems.MenuConstants.MULTI_PLAYER_MENU;

public class MultiPlayerMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = MULTI_PLAYER_MENU;

        commands.add(new SelectOppUserCommand());
        commands.add(new ShowSecondUser());
        commands.add(new StartMultiGameCommand());
        commands.add(new StartMultiGameCollectFlagsCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new InGameMenu());
    }
}
