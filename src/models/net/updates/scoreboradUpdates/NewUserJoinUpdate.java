package models.net.updates.scoreboradUpdates;

import models.net.UpdatePacket;

public class NewUserJoinUpdate extends UpdatePacket {
    private String username;

    public NewUserJoinUpdate(String username) {
        this.username = username;
    }

    @Override
    public void update() {
        // TODO Sepehr :
    }
}
