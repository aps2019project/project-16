package view.commands.multiPlayerCommands;

import controllers.MultiPlayerController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartMultiGameCommand extends Command {
    {
        name = "start game (kill hero | hold flag)";
        pattern = Pattern.compile("start game ((kill hero)|(hold flag))", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String type = matcher.group(1).toLowerCase();
        MultiPlayerController controller = new MultiPlayerController();
        switch (type) {
            case "kill hero":
                controller.startMultiGame(1, 0);
                break;
            case "hold flag":
                controller.startMultiGame(2, 1);
                break;
        }
    }
}
