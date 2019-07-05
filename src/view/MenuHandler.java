package view;

import controllers.AccountController;
import view.menuItems.AccountMenu;
import view.menuItems.MenuItem;
import view.views.AccountView;

import static view.MenuChangingState.DOWN_TO_TOP;
import static view.MenuChangingState.TOP_TO_DOWN;
import static view.Notify.logError;
import static view.Notify.logMessage;

public class MenuHandler {
    private static MenuItem currentMenu = null;

    public static MenuItem getCurrentMenu() {
        return currentMenu;
    }

    public static void goToSubMenu(String subMenuName) {
        for (MenuItem subMenu : currentMenu.getSubMenus()) {
            if (subMenu.getName().equalsIgnoreCase(subMenuName)) {
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

        showCurrentMenu();
    }

    public static void showCurrentMenu() {
        logMessage("Current menu: " + "\"" + currentMenu.getName() + "\"");
        if (currentMenu.getSubMenus().size() != 0) {
            logMessage("Sub menus:");
        }
        for (MenuItem subMenu : currentMenu.getSubMenus()) {
            logMessage("\t* " + subMenu.getName());
        }
    }

    public static void startFirstMenu() {
        new AccountController(new AccountView()).loadAccounts();
        startMenu(new AccountMenu(), TOP_TO_DOWN);
    }
}
