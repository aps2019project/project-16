package view.commands;

import java.util.regex.Pattern;

public class CreateAccountCommand extends Command {
    {
        name = "create account (username)";
        pattern = Pattern.compile("create account (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String accountName = matcher.group(1);
        // TODO: 4/19/19 call creating account and then getting password if doesn't exist
    }
}