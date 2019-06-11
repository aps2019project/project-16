package view.commands.multiPlayerCommands;

import controllers.MultiPlayerController;
import view.commands.Command;

import java.util.regex.Pattern;

public class SelectOppUserCommand extends Command {
    {
        name = "select user (opponent username)";
        pattern = Pattern.compile("select user (\\w+( \\w+)*)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String opponentUserName = matcher.group(1);
        new MultiPlayerController().selectOppUser(opponentUserName);
    }
}
