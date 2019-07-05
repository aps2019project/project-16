package models.net.updates;

import models.net.UpdatePacket;

public class RequestResultUpdate extends UpdatePacket {
    private String message;

    public RequestResultUpdate(String message) {
        this.message = message;
    }

    @Override
    public void update() {
        System.out.println(message);
    }
}
