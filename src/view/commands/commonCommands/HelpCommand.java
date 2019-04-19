package view.commands.commonCommands;

import view.MenuHandler;
import view.commands.Command;

import java.util.regex.Pattern;

public class HelpCommand extends Command {
    {
        name = "help";
        pattern = Pattern.compile("help", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        System.out.println("Commands of this menu:");
        for (Command command : MenuHandler.getCurrentMenu().getCommands()) {
            System.out.println("* " + command.getName());
        }
    }
}
