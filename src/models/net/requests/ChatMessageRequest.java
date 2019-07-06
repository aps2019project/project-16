package models.net.requests;

import models.net.RequestHandlerThread;
import models.net.RequestPacket;
import models.net.Server;
import models.net.updates.ChatUpdate;

public class ChatMessageRequest extends RequestPacket {
    private String message;

    public ChatMessageRequest(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        String accountName = ((RequestHandlerThread) Thread.currentThread()).getAccountName();
        ChatUpdate update = new ChatUpdate(accountName, message);
        Server.getInstance().broadcastPacket(update);
    }
}
