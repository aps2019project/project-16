package models.net.requests;

import models.net.RequestPacket;

public class ChatMessageRequest extends RequestPacket {
    private String message;

    // TODO: 7/5/19 call it
    public ChatMessageRequest(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        // TODO: 7/5/19
    }
}
