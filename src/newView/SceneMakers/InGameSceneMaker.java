package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import newView.BattleView.GameGraphicData;
import newView.BattleView.GraphicalGameViewer;
import newView.GraphicalElements.*;
import newView.GraphicalElements.battle.*;
import newView.GraphicalElements.effects.SnowPane;

public class InGameSceneMaker extends SceneMaker {
    private static GraphicalGameViewer gameViewer = new GraphicalGameViewer();

    public InGameSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10, "battle");

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1400, 900, "battle");
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1400 / 2, HEIGHT / 2 - 900 / 2);

        SnowPane snowPane = new SnowPane();

        HandHBox handHBox = new HandHBox();

        PlayerInfoPane[] infoPanes = new PlayerInfoPane[2];
        infoPanes[0] = new PlayerInfoPane(false);
        infoPanes[1] = new PlayerInfoPane(true);

        TilesPane tilesPane = new TilesPane();

        EndTurnButton endTurnButton = new EndTurnButton(true);

        GameGraphicData.setDatas(handHBox, endTurnButton, tilesPane, infoPanes);

        borderPane.getChildren().addAll(mapBGView, snowPane, infoPanes[0], infoPanes[1]);
        borderPane.getChildren().addAll(handHBox, tilesPane, endTurnButton);
        borderPane.setCursor(SceneMaker.GAME_CURSOR);
        return new MyScene(borderPane);
    }
}
