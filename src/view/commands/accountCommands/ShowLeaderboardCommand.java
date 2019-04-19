package view.commands.accountCommands;

import controllers.AccountController;
import view.commands.Command;

import java.util.regex.Pattern;

public class ShowLeaderboardCommand extends Command {
    {
        name = "show leaderboard";
        pattern = Pattern.compile("show leaderboard", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        AccountController controller = new AccountController();
        controller.loadLeaderboard();
    }
}
