package view.commands.commonCommands;

import view.MenuHandler;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowMenuCommand extends Command {
    {
        name = "show menu";
        pattern = Pattern.compile("show menu", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        MenuHandler.showCurrentMenu();
    }
}
