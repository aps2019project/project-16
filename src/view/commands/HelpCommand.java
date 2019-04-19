package view.commands;

import java.util.regex.Pattern;

public class HelpCommand extends Command {
    {
        name = "help";
        pattern = Pattern.compile("help", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        System.out.println("Commands of this menu:");
        // TODO: 4/19/19 foreach on commands of currentMenu and print names of them
    }
}
