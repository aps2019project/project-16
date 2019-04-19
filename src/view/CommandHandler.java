package view;

import view.menuItems.AccountMenu;
import view.menuItems.MenuItem;

import java.util.Scanner;

public class CommandHandler {
    public static MenuItem currentMenu = new AccountMenu();
    public static Scanner scanner = new Scanner(System.in);

    public static String scanCommand(String message) {
        System.out.println(message);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }
}
