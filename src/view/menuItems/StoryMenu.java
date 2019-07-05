package view.menuItems;

import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;
import view.commands.storyCommands.EnterLevelCommand;
import view.commands.storyCommands.ShowLevelsCommand;

import static view.menuItems.MenuConstants.STORY_MENU;

public class StoryMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = STORY_MENU;

        commands.add(new EnterLevelCommand());
        commands.add(new ShowLevelsCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new InGameMenu());
    }
}
