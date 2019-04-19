package view;

import java.util.Scanner;

public class CommandHandler {
    public static Scanner scanner = new Scanner(System.in);

    public static String scanCommand(String message) {
        System.out.println(message);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }
}
