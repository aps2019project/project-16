package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartGameCollectFlagsCommand extends Command {
    {
        name = "start game collect flags (number of flags)";
        pattern = Pattern.compile("start game collect flags (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int numberOfFlags = Integer.parseInt(matcher.group(1));
        new CustomGameController().startGame(3, numberOfFlags);
    }
}
