package newView.SceneMakers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.MyScene;

public class ServerScoreBoardSceneMaker extends ScoreBoardSceneMaker {


    public ServerScoreBoardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    protected void setBackOnMouseClick(Node back) {
        back.setOnMouseClicked(event -> new ServerMenuSceneMaker(getPrimaryStage()).set());
    }
}