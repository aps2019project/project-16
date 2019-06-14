package view.commands.accountCommands;

import controllers.AccountController;
import exception.AccountExistsException;
import view.CommandHandler;
import view.commands.Command;
import view.views.AccountView;

import java.util.regex.Pattern;

public class CreateAccountCommand extends Command {
    {
        name = "create account (username)";
        pattern = Pattern.compile("create account (\\w+)", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public void doIt() {
        String username = matcher.group(1);
        String password = CommandHandler.scanCommandByMessage("please set your password:");
        AccountController controller = new AccountController(new AccountView());
        try {
            controller.createAccount(username, password);
        } catch (AccountExistsException ignored) {
        }
    }
}