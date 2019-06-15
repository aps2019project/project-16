package newView.BattleView.gameActs;


import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.PlayerInfoPane;

public class ManaAct extends GameAct {
    private int playerNumber;
    private int numberOfManas;

    public ManaAct(int playerNumber, int numberOfManas) {
        this.playerNumber = playerNumber;
        this.numberOfManas = numberOfManas;
    }

    @Override
    public void showAction() {
        PlayerInfoPane[] infoPanes = GameGraphicData.getInfoPanes();
        infoPanes[playerNumber].getManaPane().setNumberOfManas(numberOfManas);
    }
}
