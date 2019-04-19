package view;

import java.util.Scanner;

import static view.Notify.logMessage;

public class CommandHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String scanCommand(String message) {
        logMessage(message);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }
}
