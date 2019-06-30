package newView.BattleView.gameActs;

import newView.SoundPlayer;

public class YaHusseinAct extends GameAct {
    @Override
    public void showAction() {
        SoundPlayer.playByPath("src/newView/resources/sounds/ya hussein.wav");
    }
}
