package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newView.GraphicalElements.*;
import newView.GraphicalElements.battle.HandHBox;
import newView.GraphicalElements.battle.TilesPane;
import newView.GraphicalElements.battle.effects.SnowPane;

import java.io.FileNotFoundException;

import static newView.GraphicalElements.battle.HandElement.HAND_LENGTH;
import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;

public class InGameSceneMaker extends SceneMaker {
    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10);

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1300, 750);
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1300 / 2, HEIGHT / 2 - 750 / 2);

        HBox handHBox = new HandHBox();
        ScaleTool.relocate(handHBox, WIDTH / 2 - HAND_LENGTH * 3.5, HEIGHT - HAND_LENGTH);

        TilesPane tilesPane = new TilesPane();
        ScaleTool.relocate(tilesPane, WIDTH / 2 - TILE_LENGTH * 5, HEIGHT / 2 - TILE_LENGTH * 3);

        SnowPane snowPane = new SnowPane();

        // TODO: 6/11/19

        borderPane.getChildren().addAll(mapBGView, snowPane, handHBox, tilesPane);
        borderPane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(borderPane);
    }
}
