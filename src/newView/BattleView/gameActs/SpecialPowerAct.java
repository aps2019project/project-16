package newView.BattleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.Tile;
import newView.GraphicalElements.battle.TilesPane;
import newView.Type;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;
import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_ROWS;

public class SpecialPowerAct extends GameAct {
    private String powerName;
    private int row;
    private int column;

    public SpecialPowerAct(int row, int column, String powerName) {
        this.row = row;
        this.column = column;
        this.powerName = powerName;
    }

    @Override
    public void showAction() {
        try {
            makeAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeAnimation() throws Exception {
        ImageView imageView = AnimationMaker.getActiveAnimation(powerName, Type.SPECIAL_POWER.getName());
        Tile putTile = GameGraphicData.getTilesPane().getTile(row, column);
        TilesPane tilesPane = GameGraphicData.getTilesPane();

        Text text = new Text("SPECIAL POWER");
        ScaleTool.relocate(text, NUMBER_OF_COLUMNS * TILE_LENGTH / 2, NUMBER_OF_ROWS * TILE_LENGTH / 2);
        tilesPane.getChildren().add(text);
        text.setScaleX(1);
        text.setScaleY(1);

        KeyValue keyValue = new KeyValue(text.scaleXProperty(), 8);
        KeyValue keyValue1 = new KeyValue(text.scaleYProperty(), 8);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.3)
                , false, 1
                ,keyValue, keyValue1);
        timeline.play();

        timeline.setOnFinished(event -> {
            tilesPane.getChildren().remove(text);
            putTile.showSpellCast(imageView);
        });
    }
}
