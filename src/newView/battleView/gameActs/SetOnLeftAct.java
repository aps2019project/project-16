package newView.battleView.gameActs;

import newView.battleView.GameGraphicData;

public class SetOnLeftAct extends GameAct{
    private boolean onLeft;

    public SetOnLeftAct(boolean onLeft) {
        this.onLeft = onLeft;
    }

    @Override
    public void showAction() {
        GameGraphicData.setOnLeft(onLeft);
    }
}
