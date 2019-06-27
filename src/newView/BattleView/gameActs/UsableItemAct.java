package newView.BattleView.gameActs;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.TilesPane;
import newView.Type;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;
import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_COLUMNS;
import static newView.GraphicalElements.battle.TilesPane.NUMBER_OF_ROWS;

public class UsableItemAct extends GameAct {
    private String playerName;
    private String name;

    public UsableItemAct(String playerName, String name) {
        this.playerName = playerName;
        this.name = name;
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
        ImageView activateView = AnimationMaker.getActiveAnimation(name, Type.ITEM.getName());
        ScaleTool.resizeImageView(activateView, 150, 150);
        ScaleTool.relocate(activateView
                , (NUMBER_OF_COLUMNS - 1) * TILE_LENGTH / 2
                , (NUMBER_OF_ROWS - 1) * TILE_LENGTH / 2);

        Text text = new Text("Player \"" + playerName + "\" casted usable item \"" + name + "\"");
        text.setScaleX(2);
        text.setScaleY(2);
        ScaleTool.relocate(text, (NUMBER_OF_COLUMNS - 3) * TILE_LENGTH / 2, (NUMBER_OF_ROWS + 3) * TILE_LENGTH / 2);

        TilesPane tilesPane = GameGraphicData.getTilesPane();
        tilesPane.getChildren().addAll(activateView, text);

        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.9)
                , false, 1);
        timeline.play();

        timeline.setOnFinished(event1 -> tilesPane.getChildren().removeAll(activateView, text));
    }
}
