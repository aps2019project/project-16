package view;

import view.menuItems.AccountMenu;
import view.menuItems.MenuItem;

import static view.MenuChangingState.*;
import static view.Notify.*;

public class MenuHandler {
    private static MenuItem currentMenu = null;

    public static MenuItem getCurrentMenu() {
        return currentMenu;
    }

    public static void goToSubMenu(String subMenuName) {
        for (MenuItem subMenu : currentMenu.getSubMenus()) {
            if (subMenu.getName().toLowerCase().equals(subMenuName.toLowerCase())) {
                startMenu(subMenu, TOP_TO_DOWN);
                return;
            }
        }
        logError("This subMenu doesn't exist.");
    }

    public static void goToParentMenu() {
        if (currentMenu.getParentMenu() == null) {
            System.exit(0);
        } else {
            startMenu(currentMenu.getParentMenu(), DOWN_TO_TOP);
        }
    }

    private static void startMenu(MenuItem menuItem, MenuChangingState state) {
        MenuItem tempParent = currentMenu;
        currentMenu = menuItem;
        if (state == TOP_TO_DOWN) {
            menuItem.setParentMenu(tempParent);
        }

        int i = 1;
        for (MenuItem subMenu : menuItem.getSubMenus()) {
            logMessage(i + ". " + subMenu.getName());
            i++;
        }
    }

    public static void startFirstMenu() {
        startMenu(new AccountMenu(), TOP_TO_DOWN);
    }
}
