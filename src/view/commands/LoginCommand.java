package view.commands;

import java.util.regex.Pattern;

public class LoginCommand extends Command {
    {
        name = "login (username)";
        pattern = Pattern.compile("login (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String accountName = matcher.group(1);
        // TODO: 4/19/19 if account exist get password and login
    }
}
