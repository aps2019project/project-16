package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class ShowCardInfoCommand extends Command {
    {
        name = "show card info (player name)_(card name)_(game card ID)";
        pattern = Pattern.compile("show card info (\\w+( \\w+)*)_(\\w+( \\w+)*)_(\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String playerName = matcher.group(1);
        String cardName = matcher.group(3);
        int gameCardID = Integer.parseInt(matcher.group(5));
        new InGameController().loadCardInfo(playerName, cardName, gameCardID);
    }
}
