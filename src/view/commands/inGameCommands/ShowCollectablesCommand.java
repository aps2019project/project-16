package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class ShowCollectablesCommand extends Command {
    {
        name = "show collectables";
        pattern = Pattern.compile("show collectables", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).loadCollectables();
    }
}
