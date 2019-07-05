package view.menuItems;

import view.commands.accountCommands.CreateAccountCommand;
import view.commands.accountCommands.LoginCommand;
import view.commands.accountCommands.ShowLeaderboardCommand;
import view.commands.commonCommands.ExitCommand;
import view.commands.commonCommands.HelpCommand;

import static view.menuItems.MenuConstants.ACCOUNT_MENU;

public class AccountMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = ACCOUNT_MENU;

        commands.add(new CreateAccountCommand());
        commands.add(new LoginCommand());
        commands.add(new ShowLeaderboardCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new MainMenu());//virtual sub menu. user can't enter this
    }
}
