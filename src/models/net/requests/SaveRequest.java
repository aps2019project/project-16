package models.net.requests;

import models.GameContents;
import models.net.RequestHandlerThread;
import models.net.RequestPacket;

import java.io.IOException;

public class SaveRequest extends RequestPacket {
    @Override
    public void run() {
        String accountName = ((RequestHandlerThread) Thread.currentThread()).getAccountName();
        if (accountName != null) {
            try {
                GameContents.saveAccount(GameContents.findAccount(accountName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
