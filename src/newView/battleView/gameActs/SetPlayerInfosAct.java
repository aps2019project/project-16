package newView.battleView.gameActs;

import newView.GraphicalElements.battle.PlayerInfoPane;
import newView.battleView.GameGraphicData;

public class SetPlayerInfosAct extends GameAct {
    private String leftName;
    private String rightName;

    public SetPlayerInfosAct(String leftName, String rightName) {
        this.leftName = leftName.toUpperCase();
        this.rightName = rightName.toUpperCase();
    }

    @Override
    public void showAction() {
        PlayerInfoPane[] infoPanes = GameGraphicData.getInfoPanes();
        infoPanes[0].setPlayerName(leftName);
        infoPanes[1].setPlayerName(rightName);
    }
}
