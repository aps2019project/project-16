package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartGameCommand extends Command {
    {
        name = "start game (kill hero | hold flag)";
        pattern = Pattern.compile("start game ((kill hero)|(hold flag))", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String type = matcher.group(1).toLowerCase();
        switch (type) {
            case "kill hero":
                new CustomGameController().startGame(1, 0);
                break;
            case "hold flag":
                new CustomGameController().startGame(2, 1);
                break;
        }
    }
}
