package view.commands.mainMenuCommands;

import controllers.AccountController;
import view.commands.Command;

import java.util.regex.Pattern;

public class SaveCommand extends Command {
    {
        name = "save";
        pattern = Pattern.compile("save", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new AccountController().saveGameData();
    }
}
