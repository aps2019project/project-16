package models.net.updates;

import models.net.UpdatePacket;
import newView.battleView.gameActs.GameAct;

public class BattleUpdate extends UpdatePacket {
    private GameAct gameAct;

    // TODO mostafa: 7/5/19 must be called and send to client.
    public BattleUpdate(GameAct gameAct) {
        this.gameAct = gameAct;
    }

    @Override
    public void update() {
        // TODO must implemented.
    }
}
