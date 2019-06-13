package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import newView.GraphicalElements.*;
import newView.GraphicalElements.battle.*;
import newView.GraphicalElements.effects.SnowPane;
import newView.menu.Page;

import java.io.FileNotFoundException;

public class InGameSceneMaker extends SceneMaker {

    public InGameSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10, "battle");

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1300, 750, "battle");
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1300 / 2, HEIGHT / 2 - 750 / 2);

        HBox handHBox = new HandHBox();

        TilesPane tilesPane = new TilesPane();

        SnowPane snowPane = new SnowPane();

        PlayerInfoPane playerInfoPane1 = new PlayerInfoPane(false);
        PlayerInfoPane playerInfoPane2 = new PlayerInfoPane(true);

        EndTurnButton endTurnButton = new EndTurnButton(true);

        // TODO: 6/11/19

        borderPane.getChildren().addAll(mapBGView, snowPane, playerInfoPane1, playerInfoPane2);
        borderPane.getChildren().addAll(handHBox, tilesPane, endTurnButton);
        borderPane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(borderPane);
    }
}
