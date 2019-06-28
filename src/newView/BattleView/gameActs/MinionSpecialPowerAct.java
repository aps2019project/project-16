package newView.BattleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.SpecialPowerCastTime;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.Tile;
import newView.GraphicalElements.battle.TilesPane;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;
import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;

public class MinionSpecialPowerAct extends GameAct {
    private SpecialPowerCastTime time;
    private int row;
    private int column;

    public MinionSpecialPowerAct(SpecialPowerCastTime time, int row, int column) {
        this.time = time;
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        makeAnimation();
    }

    private void makeAnimation() {
        TilesPane tilesPane = GameGraphicData.getTilesPane();
        Tile tile = tilesPane.getTile(row, column);
        tile.enableColorAnimation(Color.LIGHTSALMON);

        Text timeCastName = new Text(time.name());
        timeCastName.setScaleX(8);
        timeCastName.setScaleY(8);
        timeCastName.setFill(Color.LIGHTBLUE);
        ScaleTool.relocate(timeCastName, TILE_LENGTH * NUMBER_OF_COLUMNS / 2, -200);

        tilesPane.getChildren().add(timeCastName);

        KeyValue keyValue = new KeyValue(timeCastName.yProperty(), timeCastName.getY() + 500);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.8)
                , false, 1
                , keyValue);
        timeline.play();

        timeline.setOnFinished(event -> tilesPane.getChildren().remove(timeCastName));
    }
}
