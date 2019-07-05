package models.net.requests;

import controllers.AccountController;
import models.net.RequestPacket;

public class SignUpRequest extends RequestPacket {
    private String username;
    private String password;

    // TODO mostafa: 7/5/19 call it and send to server
    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        new AccountController().createAccount(username, password);
    }
}
