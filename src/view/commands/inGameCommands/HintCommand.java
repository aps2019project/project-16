package view.commands.inGameCommands;

import controllers.InGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class HintCommand extends Command {
    {
        name = "hint";
        pattern = Pattern.compile("hint", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        new InGameController().loadGameHint();
    }
}
