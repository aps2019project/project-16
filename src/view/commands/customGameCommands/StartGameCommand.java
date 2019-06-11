package view.commands.customGameCommands;

import controllers.CustomGameController;
import view.commands.Command;

import java.util.regex.Pattern;

public class StartGameCommand extends Command {
    {
        name = "start game (opponent deck name) (kill hero | hold flag)";
        pattern = Pattern.compile("start game (\\w+( \\w+)*) ((kill hero)|(hold flag))", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String oppDeckName = matcher.group(1);
        String type = matcher.group(3).toLowerCase();
        switch (type) {
            case "kill hero":
                new CustomGameController().startGame(oppDeckName,1, 0);
                break;
            case "hold flag":
                new CustomGameController().startGame(oppDeckName,2, 1);
                break;
        }
    }
}
