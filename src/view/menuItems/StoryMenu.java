package view.menuItems;

import view.commands.commonCommands.EnterMenuCommand;
import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.STORY_MENU;

public class StoryMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = STORY_MENU;

        // TODO: 4/21/19
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //no subMenu
    }
}
