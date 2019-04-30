package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartGameCollectFlagsCommand extends Command {
    {
        name = "start game (opponent deck name) collect flags (number of flags)";
        pattern = Pattern.compile("start game (\\w+( \\w+)*) collect flags (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppDeckName = matcher.group(1);
        int numberOfFlags = Integer.parseInt(matcher.group(3));
        new CustomGameController().startGame(oppDeckName, 3, numberOfFlags);
    }
}
