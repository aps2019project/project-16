package models.net.updates.watchUpdates;

import ir.pas.ClientApp;
import javafx.application.Platform;
import models.net.UpdatePacket;
import newView.SceneMakers.InGameSceneMaker;
import newView.battleView.GameGraphicData;
import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;

public class FastShowUpdate extends UpdatePacket {
    private ArrayList<GameAct> gameActs;

    // TODO Mostafa: 7/5/19
    public FastShowUpdate(ArrayList<GameAct> gameActs) {
        this.gameActs = gameActs;
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
            GameGraphicData.setSpectator(true);
            new InGameSceneMaker(ClientApp.getPrimaryStage()).set();
            GameGraphicData.showActionsInFastRate(gameActs);
        });
    }
}
