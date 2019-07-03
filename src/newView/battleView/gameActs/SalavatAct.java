package newView.battleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.battle.TilesPane;
import newView.SoundPlayer;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class SalavatAct extends GameAct {
    @Override
    public void showAction() {
        TilesPane tilesPane = GameGraphicData.getTilesPane();

        SoundPlayer.playByPath("src/newView/resources/sounds/salavat.wav");

        KeyValue keyValue = new KeyValue(tilesPane.layoutXProperty(), tilesPane.getLayoutX() + 50);
        KeyValue keyValue1 = new KeyValue(tilesPane.layoutYProperty(), tilesPane.getLayoutY() - 50);
        KeyValue keyValue2 = new KeyValue(tilesPane.rotateProperty(), 10);
        KeyValue keyValue3 = new KeyValue(tilesPane.scaleXProperty(), tilesPane.getScaleX() * 1.15);
        KeyValue keyValue4 = new KeyValue(tilesPane.scaleYProperty(), tilesPane.getScaleY() * 1.15);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.04)
                , true, 16
                , keyValue, keyValue1, keyValue2, keyValue3, keyValue4);
        timeline.play();
    }
}
