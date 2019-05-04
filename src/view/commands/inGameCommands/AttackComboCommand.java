package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttackComboCommand extends Command {
    {
        name = "attack combo (opponent card name)_(game ID) (my card name)_(game ID) (my card name)_(game ID) (...)";
        pattern = Pattern.compile("attack combo" + " (\\w+( \\w+)*_\\d+)" + "( \\w+( \\w+)*_\\d+)*", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppCardID = matcher.group(1);
        ArrayList<String> myCardIDs = new ArrayList<>();
        Pattern cardIDPattern = Pattern.compile("(\\w+( \\w+)*_\\d+)", Pattern.CASE_INSENSITIVE);
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
