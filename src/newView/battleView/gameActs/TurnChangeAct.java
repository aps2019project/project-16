package newView.battleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.TilesPane;
import newView.SoundPlayer;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;
import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_ROWS;

public class TurnChangeAct extends GameAct {
    @Override
    public void showAction() {
        makeAnimation();
    }

    private void makeAnimation() {
        Text text = new Text("NEXT TURN!!");
        ScaleTool.relocate(text, NUMBER_OF_COLUMNS * TILE_LENGTH / 2, NUMBER_OF_ROWS * TILE_LENGTH / 2);
        text.setScaleX(4);
        text.setScaleY(4);

        SoundPlayer.playByPath("src/newView/resources/sounds/next.wav");

        TilesPane tilesPane = GameGraphicData.getTilesPane();
        tilesPane.getChildren().add(text);

        KeyValue keyValue = new KeyValue(text.xProperty(), 8);
        KeyValue keyValue1 = new KeyValue(text.yProperty(), 8);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.9)
                , true, 2
                , keyValue, keyValue1);
        timeline.play();

        timeline.setOnFinished(event -> tilesPane.getChildren().remove(text));

        GameGraphicData.getTurnButton().changeState();
    }
}
