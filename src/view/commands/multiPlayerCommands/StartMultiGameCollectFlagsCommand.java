package view.commands.multiPlayerCommands;

import controllers.MultiPlayerController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartMultiGameCollectFlagsCommand extends Command {
    {
        name = "start game collect flags (number of flags)";
        pattern = Pattern.compile("start game collect flags (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int numberOfFlags = Integer.parseInt(matcher.group(1));
        new MultiPlayerController().startMultiGame(3,numberOfFlags);
    }
}
