package models.net.updates;

import ir.pas.ClientApp;
import javafx.application.Platform;
import models.net.Client;
import models.net.UpdatePacket;
import newView.SceneMakers.MainMenuSceneMaker;

public class LoginSuccessUpdate extends UpdatePacket {
    private String authToken;

    public LoginSuccessUpdate(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public void update() {
        Client.getInstance().setAuthToken(authToken);
        Platform.runLater(() -> new MainMenuSceneMaker(ClientApp.getPrimaryStage()).set());
    }
}
