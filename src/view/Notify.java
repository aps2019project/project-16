package view;

import models.net.RequestHandlerThread;
import models.net.updates.RequestResultUpdate;

public class Notify {
    public static void logError(String message) {
        if (Thread.currentThread() instanceof RequestHandlerThread)
            ((RequestHandlerThread) Thread.currentThread()).getServerSideClient().sendPacket(
                    new RequestResultUpdate(message)
            );
    }

    public static void logMessage(String message) {
        if (Thread.currentThread() instanceof RequestHandlerThread)
            ((RequestHandlerThread) Thread.currentThread()).getServerSideClient().sendPacket(
                    new RequestResultUpdate(message)
            );
    }
}
