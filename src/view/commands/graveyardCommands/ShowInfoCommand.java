package view.commands.graveyardCommands;

import controllers.GraveyardController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowInfoCommand extends Command {
    {
        name = "show info (card name) (game card ID)";
        pattern = Pattern.compile("show info (\\w+( \\w+)*) (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardName = matcher.group(3);
        int gameCardID = Integer.parseInt(matcher.group(4));
        new GraveyardController().loadCard(cardName, gameCardID);
    }
}
