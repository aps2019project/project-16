package models.net.requests;

import models.net.RequestHandlerThread;
import models.net.RequestPacket;

public class LogoutRequest extends RequestPacket {
    @Override
    public void run() {
        ((RequestHandlerThread) Thread.currentThread()).setAccountName(null);
    }
}
