package view.menuItems;

import view.commands.*;

public class AccountMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        menuName = "Account";
        parentMenu = null;

        commands.add(new CreateAccountCommand());
        commands.add(new LoginCommand());
        commands.add(new ShowLeaderboardCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        //doesn't have any subMenu
    }
}
