package view.menuItems;

import view.commands.*;

import static view.menuItems.MenuConstants.ACCOUNT_MENU;

public class AccountMenu extends MenuItem {

    @Override
    protected void initMenuItem() {
        name = ACCOUNT_MENU;
        parentMenu = null;

        commands.add(new CreateAccountCommand());
        commands.add(new LoginCommand());
        commands.add(new ShowLeaderboardCommand());
        commands.add(new ExitCommand());
        commands.add(new HelpCommand());

        subMenus.add(new MainMenu());
    }
}
