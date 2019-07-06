package models.net.updates.watchUpdates;

import models.net.UpdatePacket;
import newView.battleView.gameActs.GameAct;

import java.util.ArrayList;

public class MatchReplayUpdate extends UpdatePacket {
    private ArrayList<GameAct> gameActs;

    public MatchReplayUpdate(ArrayList<GameAct> gameActs) {
        this.gameActs = gameActs;
    }

    @Override
    public void update() {
        // TODO Sadegh: 7/6/19
    }
}
