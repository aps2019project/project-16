package view.commands.mainMenuCommands;

import view.commands.Command;

import java.util.regex.Pattern;

public class SaveCommand extends Command {
    {
        name = "save";
        pattern = Pattern.compile("save", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 save data
    }
}
