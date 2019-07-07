package models.net.updates;

import models.net.UpdatePacket;
import newView.battleView.GameGraphicData;
import newView.battleView.gameActs.GameAct;

public class BattleUpdate extends UpdatePacket {
    private GameAct gameAct;

    public BattleUpdate(GameAct gameAct) {
        this.gameAct = gameAct;
    }

    @Override
    public void update() {
        GameGraphicData.addGameAct(gameAct);
    }
}
