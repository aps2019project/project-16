package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class ShowCardInfoCommand extends Command {
    {
        name = "show card info (card ID)";
        pattern = Pattern.compile("show card info " + CARD_ID_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardID = matcher.group(1);
        new InGameController().loadCardInfo(cardID);
    }
}
