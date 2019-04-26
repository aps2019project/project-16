package view.commands.graveyardCommands;

import controllers.GraveyardController;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class ShowInfoCommand extends Command {
    {
        name = "show info (card ID)";
        pattern = Pattern.compile("show info " + CARD_ID_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String cardID = matcher.group(1);
        new GraveyardController().loadCard(cardID);
    }
}
