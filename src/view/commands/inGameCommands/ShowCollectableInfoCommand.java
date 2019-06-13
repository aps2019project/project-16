package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;
import view.views.InGameView;

import java.util.regex.Pattern;

public class ShowCollectableInfoCommand extends Command {
    {
        name = "show info";
        pattern = Pattern.compile("show info", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController(new InGameView()).loadSelectedCollectableInfo();
    }
}
