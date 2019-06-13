package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class MoveCommand extends Command {
    {
        name = "move to [x, y]";
        pattern = Pattern.compile("move to \\[(\\d+), *(\\d+)\\]", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        new InGameController(new InGameView()).moveToCell(x, y);
    }
}
