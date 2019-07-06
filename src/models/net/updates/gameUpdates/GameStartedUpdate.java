package models.net.updates.gameUpdates;

import ir.pas.ClientApp;
import javafx.application.Platform;
import models.net.UpdatePacket;
import newView.SceneMakers.InGameSceneMaker;
import newView.battleView.GameGraphicData;

public class GameStartedUpdate extends UpdatePacket {
    @Override
    public void update() {
        Platform.runLater(() -> {
            GameGraphicData.setSpectator(false);
            new InGameSceneMaker(ClientApp.getPrimaryStage()).set();
        });
    }
}
