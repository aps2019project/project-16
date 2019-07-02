package newView.battleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.TilesPane;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_ROWS;

public class GameEndAct extends GameAct {
    private String winnerName;

    public GameEndAct(String winnerName) {
        this.winnerName = winnerName.toUpperCase();
    }

    @Override
    public void showAction() {
        makeAnimation();
    }

    private void makeAnimation() {
        Text text = new Text("WINNER is " + winnerName);
        text.setScaleX(0.01);
        text.setScaleY(0.01);
        text.setFill(Color.LIGHTBLUE);
        ScaleTool.relocate(text, TILE_LENGTH * NUMBER_OF_COLUMNS / 2, TILE_LENGTH * NUMBER_OF_ROWS / 2);

        TilesPane tilesPane = GameGraphicData.getTilesPane();

        tilesPane.getChildren().add(text);

        KeyValue keyValue = new KeyValue(text.scaleXProperty(), 8);
        KeyValue keyValue1 = new KeyValue(text.scaleYProperty(), 8);
        KeyValue keyValue2 = new KeyValue(text.rotateProperty(), 360);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(4000)
                , false, 1
                , keyValue, keyValue1, keyValue2);
        timeline.play();
    }
}
