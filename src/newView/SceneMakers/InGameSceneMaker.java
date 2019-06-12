package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newView.GraphicalElements.*;
import newView.GraphicalElements.battle.*;
import newView.GraphicalElements.battle.effects.SnowPane;

import java.io.FileNotFoundException;

public class InGameSceneMaker extends SceneMaker {
    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10);

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1300, 750);
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1300 / 2, HEIGHT / 2 - 750 / 2);

        HBox handHBox = new HandHBox();

        TilesPane tilesPane = new TilesPane();

        SnowPane snowPane = new SnowPane();

        PlayerInfoPane playerInfoPane1 = new PlayerInfoPane(false);
        PlayerInfoPane playerInfoPane2 = new PlayerInfoPane(true);

        // TODO: 6/11/19

        borderPane.getChildren().addAll(mapBGView, snowPane, playerInfoPane1, playerInfoPane2);
        borderPane.getChildren().addAll(handHBox, tilesPane);
        borderPane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(borderPane);
    }
}
