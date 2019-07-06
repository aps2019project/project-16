package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.ScoreBoardRequest;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.scoreBoard.ScoreBoardPane;

import java.io.FileInputStream;

public class ScoreBoardSceneMaker extends SceneMaker {
    private static ScoreBoardPane scoreBoardPane = new ScoreBoardPane();

    public ScoreBoardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Client.getInstance().sendPacket(new ScoreBoardRequest());
        Pane pane = new Pane();
        BackgroundMaker.setBackgroundFor(pane, 1, "scoreBoard");

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/scoreBoard/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());
        pane.getChildren().add(scoreBoardPane);
        pane.getChildren().add(back);
        return new MyScene(pane);
    }

    public static ScoreBoardPane getScoreBoardPane() {
        return scoreBoardPane;
    }
}
