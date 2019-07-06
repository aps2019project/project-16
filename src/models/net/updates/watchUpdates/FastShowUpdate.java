package models.net.updates.watchUpdates;

import models.net.UpdatePacket;
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
        // TODO: 7/5/19
    }
}
