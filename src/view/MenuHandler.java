package view;

import view.menuItems.AccountMenu;
import view.menuItems.MenuItem;

public class MenuHandler {
    private static MenuItem currentMenu = new AccountMenu();

    private static void startMenu(MenuItem menuItem) {
        currentMenu = menuItem;
        int i = 1;
        for (MenuItem subMenu : menuItem.getSubMenus()) {
            System.out.println(i + "." + subMenu.getName());
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
        System.err.println("This subMenu doesn't exist.");
    }

    public static void goToParentMenu() {
        if (currentMenu.getParentMenu() == null) {
            System.exit(0);
        } else {
            startMenu(currentMenu.getParentMenu());
        }
    }
}
