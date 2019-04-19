package view.menuItems;

import view.commands.commonCommands.*;

import static view.menuItems.MenuConstants.SINGLE_PLAYER_MENU;

public class SinglePlayerMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = SINGLE_PLAYER_MENU;

        commands.add(new EnterMenuCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new StoryMenu());
        subMenus.add(new CustomGameMenu());
    }
}
