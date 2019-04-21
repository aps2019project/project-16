package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class AttackCommand extends Command {
    {
        name = "attack (opponent card ID)";
        pattern = Pattern.compile("attack " + CARD_ID_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppCardID = matcher.group(1);
        new InGameController().attack(oppCardID);
    }
}
