package models.net.requests;

import controllers.AccountController;
import models.net.RequestPacket;

public class LoginRequest extends RequestPacket {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        new AccountController().loginAccount(username, password);
    }
}
