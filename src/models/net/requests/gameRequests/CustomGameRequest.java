package models.net.requests.gameRequests;

import models.net.RequestPacket;

public class CustomGameRequest extends RequestPacket {
    private String oppDeckName;
    private int mode;
    private int flags;

    public CustomGameRequest(String oppDeckName, int mode, int flags) {
        this.oppDeckName = oppDeckName;
        this.mode = mode;
        this.flags = flags;
    }

    @Override
    public void run() {
        // TODO mostafa
    }
}
