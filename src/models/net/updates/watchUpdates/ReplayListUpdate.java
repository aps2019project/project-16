package models.net.updates.watchUpdates;

import javafx.application.Platform;
import models.MatchDetail;
import models.net.UpdatePacket;
import newView.SceneMakers.ReplayMatchesSceneMaker;

import java.util.ArrayList;

public class ReplayListUpdate extends UpdatePacket {
    private ArrayList<MatchDetail> matchDetails;

    public ReplayListUpdate(ArrayList<MatchDetail> matchDetails) {
        this.matchDetails = matchDetails;
    }

    @Override
    public void update() {
        Platform.runLater(() -> ReplayMatchesSceneMaker.updateArchivedMatchDetailes(matchDetails));
    }
}
