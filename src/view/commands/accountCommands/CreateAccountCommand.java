package view.commands.accountCommands;

import controllers.AccountController;
import view.CommandHandler;
import view.commands.Command;

import java.util.regex.Pattern;

public class CreateAccountCommand extends Command {
    {
        name = "create account (username)";
        pattern = Pattern.compile("create account (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String username = matcher.group(1);
        String password = CommandHandler.scanCommand("please set your password:");
        AccountController controller = new AccountController();
        controller.createAccount(username, password);
    }
}