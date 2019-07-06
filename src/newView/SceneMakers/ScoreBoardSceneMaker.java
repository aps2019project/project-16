package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;

public class ScoreBoardSceneMaker extends SceneMaker {
    public ScoreBoardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();
        BackgroundMaker.setBackgroundFor(pane, 1, "scoreBoard");

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/scoreBoard/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());


        pane.getChildren().add(back);
        return new MyScene(pane);
    }
}
