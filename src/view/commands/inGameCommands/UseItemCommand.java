package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class UseItemCommand extends Command {
    {
        name = "use [x, y]";
        pattern = Pattern.compile("use \\[(\\d+), *(\\d+)\\]", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        new InGameController(new InGameView()).useSelectedCollectable(x, y);
    }
}
