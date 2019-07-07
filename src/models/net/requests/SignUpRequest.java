package models.net.requests;

import controllers.AccountController;
import models.net.RequestPacket;

public class SignUpRequest extends RequestPacket {
    private String username;
    private String password;

    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        new AccountController().createAccount(username, password);
    }
}
