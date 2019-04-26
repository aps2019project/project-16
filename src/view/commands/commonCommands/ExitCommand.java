package view.commands.commonCommands;

import controllers.InGameController;
import view.MenuHandler;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class ExitCommand extends Command {
    {
        name = "exit";
        pattern = Pattern.compile("exit", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        if (MenuHandler.getCurrentMenu().getName().equals(IN_GAME_MENU)) {
            new InGameController().finishTheGame();
        } else {
            MenuHandler.goToParentMenu();
        }
    }
}
