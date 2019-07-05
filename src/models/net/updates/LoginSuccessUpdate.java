package models.net.updates;

import models.net.UpdatePacket;

public class LoginSuccessUpdate extends UpdatePacket {
    private String authToken;

    // TODO mostafa: 7/5/19 must be called and send to client
    public LoginSuccessUpdate(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public void update() {
        // TODO mostafa: set authToken for client
        //  + go to main menu
    }
}
