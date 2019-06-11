package view.commands.commonCommands;

import controllers.CollectionController;
import models.GameContents;
import view.MenuHandler;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.menuItems.MenuConstants.BATTLE_MENU;
import static view.menuItems.MenuConstants.MAIN_MENU;

public class EnterMenuCommand extends Command {
    {
        name = "Enter (menu name)";
        pattern = Pattern.compile("enter (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String menuName = matcher.group(1);
        if (MenuHandler.getCurrentMenu().getName().equalsIgnoreCase(MAIN_MENU) && menuName.equalsIgnoreCase(BATTLE_MENU)) {
            new CollectionController().validateMainDeckForEnterBattle();
            return;
        }
        MenuHandler.goToSubMenu(menuName);
    }
}
