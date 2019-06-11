package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class SelectCollectableCommand extends Command {
    {
        name = "select (collectable ID)";
        pattern = Pattern.compile("select (\\d+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        int collectableID = Integer.parseInt(matcher.group(1));
        new InGameController().selectCollectable(collectableID);
    }
}
