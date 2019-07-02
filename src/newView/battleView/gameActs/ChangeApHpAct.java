package newView.battleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;
import newView.GraphicalElements.battle.TilesPane;

import static newView.battleView.GameGraphicListener.GAME_ACT_TIME;

public class ChangeApHpAct extends GameAct {
    private int row;
    private int column;
    private boolean forHP;
    private boolean poison;
    private int delta;

    public ChangeApHpAct(int row, int column, boolean forHP, int delta, boolean poison) {
        this.row = row;
        this.column = column;
        this.forHP = forHP;
        this.delta = delta;
        this.poison = poison;
    }

    @Override
    public void showAction() {
        TilesPane tilesPane = GameGraphicData.getTilesPane();
        Tile tile = tilesPane.getTile(row, column);

        String change = delta > 0 ? "+" + delta : "" + delta;
        Text text = forHP ? new Text("HP " + change) : new Text("AP " + change);
        if (poison) {
            text.setText("POISON " + text.getText());
        }
        text.setScaleX(3);
        text.setScaleY(3);
        text.setFill(Color.YELLOWGREEN);
        tile.getChildren().add(text);

        tile.enableColorAnimation(Color.CHOCOLATE);
        KeyValue keyValue = new KeyValue(text.fillProperty(), Color.BLACK);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.8)
                , true, 3
                , keyValue);
        timeline.play();
        timeline.setOnFinished(event -> tile.getChildren().remove(text));
    }
}
