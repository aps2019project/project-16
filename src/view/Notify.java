package view;

import models.net.Server;
import models.net.updates.RequestResultUpdate;

public class Notify {
    public static void logError(String message) {
        Server.getInstance().sendPacketByThread(new RequestResultUpdate(message));
    }

    public static void logMessage(String message) {
        Server.getInstance().sendPacketByThread(new RequestResultUpdate(message));
    }
}
