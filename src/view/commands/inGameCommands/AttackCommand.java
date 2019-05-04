package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class AttackCommand extends Command {
    {
        name = "attack (opponent card name) (game ID)";
        pattern = Pattern.compile("attack (\\w+( \\w+)*) (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppCardName = matcher.group(1);
        int gameID = Integer.parseInt(matcher.group(3));
        new InGameController().attack(oppCardName, gameID);
    }
}
