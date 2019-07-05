package models.net.requests;

import models.net.RequestPacket;

public class LoginRequest extends RequestPacket {
    private String username;
    private String password;

    // TODO mostafa: 7/5/19 call it and send to server
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        // TODO: 7/5/19 mostafa
    }
}
