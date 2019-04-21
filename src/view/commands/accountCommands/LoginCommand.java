package view.commands.accountCommands;

import controllers.AccountController;
import view.CommandHandler;
import view.commands.Command;

import java.util.regex.Pattern;

public class LoginCommand extends Command {
    {
        name = "login (username)";
        pattern = Pattern.compile("login (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String username = matcher.group(1);
        String password = CommandHandler.scanCommandByMessage("Please put your password:");
        AccountController controller = new AccountController();
        controller.loginAccount(username, password);
    }
}
