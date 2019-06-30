package newView.BattleView.gameActs;

import newView.SoundPlayer;

public class YaAbalfazlAct extends GameAct {
    @Override
    public void showAction() {
        SoundPlayer.playByPath("src/newView/resources/sounds/ya abalfazl.wav");
    }
}
