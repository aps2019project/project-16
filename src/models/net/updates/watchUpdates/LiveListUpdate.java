package models.net.updates.watchUpdates;

import models.MatchDetail;
import models.net.UpdatePacket;

import java.util.ArrayList;

public class LiveListUpdate extends UpdatePacket {
    private ArrayList<MatchDetail> matchDetails;

    // TODO mostafa: 7/6/19
    public LiveListUpdate(ArrayList<MatchDetail> matchDetails) {
        this.matchDetails = matchDetails;
    }

    @Override
    public void update() {
        // TODO Sepehr: 7/6/19
    }
}
