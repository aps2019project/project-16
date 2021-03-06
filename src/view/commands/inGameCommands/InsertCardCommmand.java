package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class InsertCardCommmand extends Command {
    {
        name = "insert (card name) in [x, y]";
        pattern = Pattern.compile("insert (\\w+( \\w+)*) in \\[(\\d+), *(\\d+)\\]", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        int x = Integer.parseInt(matcher.group(3));
        int y = Integer.parseInt(matcher.group(4));
        new InGameController(new InGameView()).insertCard(cardName, x, y);
    }
}
