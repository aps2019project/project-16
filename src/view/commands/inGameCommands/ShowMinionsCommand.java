package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowMinionsCommand extends Command {
    {
        name = "show (my | opponent) minions";
        pattern = Pattern.compile("show ((my)|(opponent)) minions", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String forWho = matcher.group(1);
        boolean myMinions = forWho.equalsIgnoreCase("my");
        new InGameController().loadMinions(myMinions);
    }
}
