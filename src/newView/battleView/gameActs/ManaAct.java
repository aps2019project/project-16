package newView.battleView.gameActs;


import newView.GraphicalElements.battle.PlayerInfoPane;
import newView.battleView.GameGraphicData;

public class ManaAct extends GameAct {
    private int numberOfManas;
    private boolean forLeft;

    public ManaAct(boolean forLeft, int numberOfManas) {
        this.forLeft = forLeft;
        this.numberOfManas = numberOfManas;
    }

    @Override
    public void showAction() {
        int playerNumber;
        if (forLeft) {
            playerNumber = 0;
        } else {
            playerNumber = 1;
        }
        PlayerInfoPane[] infoPanes = GameGraphicData.getInfoPanes();
        infoPanes[playerNumber].getManaPane().setNumberOfManas(numberOfManas);
    }
}
