package newView.BattleView.gameActs;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;
import newView.Type;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class MoveAct extends GameAct {
    private String unitName = "afsane";//todo must be in constructor
    private Type type = Type.HERO;//todo must be in constructor
    private int startRow;
    private int startColumn;
    private int destinationRow;
    private int destinationColumn;

    public MoveAct(int startRow, int startColumn, int destinationRow, int destinationColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.destinationRow = destinationRow;
        this.destinationColumn = destinationColumn;
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
        ImageView runningView = AnimationMaker.getRunningAnimation(unitName, type.getName());
        ImageView breathingView = AnimationMaker.getBreathingAnimation(unitName, type.getName());

        Tile source = GameGraphicData.getTilesPane().getTile(startRow, startColumn);
        Tile destination = GameGraphicData.getTilesPane().getTile(destinationRow, destinationColumn);

        source.setImageView(runningView);
        ImageView unitView = source.getImageView();

        KeyValue xKeyValue = new KeyValue(unitView.xProperty(), destination.getX() - source.getX());
        KeyValue yKeyValue = new KeyValue(unitView.yProperty(), destination.getY() - source.getY());
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.9)
                , false, 1
                , xKeyValue, yKeyValue);
        timeline.play();

        timeline.setOnFinished(event -> {
            source.setImageView(null);
            destination.setImageView(breathingView);
        });
    }
}
