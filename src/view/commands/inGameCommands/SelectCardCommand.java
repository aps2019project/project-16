package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;


public class SelectCardCommand extends Command {
    {
        name = "select (unit name) (game ID)";
        pattern = Pattern.compile("select (\\w+( \\w+)*) (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(1);
        int gameID = Integer.parseInt(matcher.group(3));
        new InGameController(new InGameView()).selectCard(cardName, gameID);
    }
}
