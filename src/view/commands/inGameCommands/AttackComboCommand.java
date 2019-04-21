package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.commands.inGameCommands.CardIDRegex.CARD_ID_REGEX;

public class AttackComboCommand extends Command {
    {
        name = "attack combo (opponent card ID) (my card ID) (my card ID) (...)";
        pattern = Pattern.compile("attack combo " + CARD_ID_REGEX + "( (\\w+_\\w+_\\d+))*", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppCardID = matcher.group(1);
        ArrayList<String> myCardIDs = new ArrayList<>();
        Pattern cardIDPattern = Pattern.compile(CARD_ID_REGEX);
        Matcher myCardsMatcher = cardIDPattern.matcher(matcher.group(0));
        int count = 0;
        while (myCardsMatcher.find()) {
            if (count != 0) {
                myCardIDs.add(myCardsMatcher.group(1));
            }
            count++;
        }
        new InGameController().attackCombo(oppCardID, myCardIDs);
    }
}
