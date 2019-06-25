package newView.BattleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;
import newView.Type;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class DieUnitAct extends GameAct {
    private int row;
    private int column;
    private String unitName;
    private Type type;

    public DieUnitAct(int row, int column, String unitName, Type type) {
        this.row = row;
        this.column = column;
        this.unitName = unitName;
        this.type = type;
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
        ImageView dieView = AnimationMaker.getDeathAnimation(unitName, type.getName());

        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.setImageView(dieView, null);
        tile.enableColorAnimation(Color.BLACK);

        KeyValue keyValue = new KeyValue(dieView.rotateProperty(), 90);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.9)
                , false, 1
                , keyValue);
        timeline.play();

        timeline.setOnFinished(event -> tile.setImageView(null, null));
    }
}
