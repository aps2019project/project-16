package view;

import view.menuItems.MenuItem;

import static view.Notify.*;

public class MenuHandler {
    private static MenuItem currentMenu = null;

    private static void startMenu(MenuItem menuItem) {
        MenuItem parent = currentMenu;
        currentMenu = menuItem;
        menuItem.setParentMenu(parent);

        int i = 1;
        for (MenuItem subMenu : menuItem.getSubMenus()) {
            logMessage(i + ". " + subMenu.getName());
            i++;
        }
    }

    public static MenuItem getCurrentMenu() {
        return currentMenu;
    }

    public static void goToSubMenu(String subMenuName) {
        for (MenuItem subMenu : currentMenu.getSubMenus()) {
            if (subMenu.getName().equals(subMenuName)) {
                startMenu(subMenu);
                return;
            }
        }
        logError("This subMenu doesn't exist.");
    }

    public static void goToParentMenu() {
        if (currentMenu.getParentMenu() == null) {
            System.exit(0);
        } else {
            startMenu(currentMenu.getParentMenu());
        }
    }
}
