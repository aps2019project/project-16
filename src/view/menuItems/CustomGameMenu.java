package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.customGameCommands.ShowOppDecksCommand;
import view.commands.customGameCommands.StartGameCollectFlagsCommand;
import view.commands.customGameCommands.StartGameCommand;

import static view.menuItems.MenuConstants.CUSTOM_GAME_MENU;

public class CustomGameMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = CUSTOM_GAME_MENU;

        commands.add(new StartGameCommand());
        commands.add(new StartGameCollectFlagsCommand());
        commands.add(new ShowOppDecksCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new InGameMenu());
    }
}
