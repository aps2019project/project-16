package view.commands;

import java.util.regex.Pattern;

public class ShowLeaderboardCommand extends Command {
    {
        name = "show leaderboard";
        pattern = Pattern.compile("show leaderboard", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        // TODO: 4/19/19 getLeaderbord and print
    }
}
