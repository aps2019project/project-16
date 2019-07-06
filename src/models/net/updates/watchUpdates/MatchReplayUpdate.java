package models.net.updates.watchUpdates;

import ir.pas.ClientApp;
import javafx.application.Platform;
import models.net.UpdatePacket;
import newView.SceneMakers.InGameSceneMaker;
import newView.battleView.GameGraphicData;
import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;

public class MatchReplayUpdate extends UpdatePacket {
    private ArrayList<GameAct> gameActs;

    public MatchReplayUpdate(ArrayList<GameAct> gameActs) {
        this.gameActs = gameActs;
    }

    @Override
    public void update() {
        Platform.runLater(() -> {
            GameGraphicData.setSpectator(true);
            new InGameSceneMaker(ClientApp.getPrimaryStage()).set();
            for (GameAct gameAct : gameActs) {
                GameGraphicData.addGameAct(gameAct);
            }
        });
    }
}
