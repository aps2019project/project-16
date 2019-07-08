package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

public class ServerMenuSceneMaker extends SceneMaker {

    public ServerMenuSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();

        Text server = new Text("SERVER");
        server.setStyle("-fx-text-fill: black ; -fx-font-size: 350");
        ScaleTool.relocate(server, 70, 400);

        VBox vBox = new VBox();

        Text shop = new Text("Shop");
        shop.setStyle("-fx-text-fill: black");
        shop.setOnMouseClicked(event -> new ServerShopSceneMaker(getPrimaryStage()).set());

        Text scoreBoard = new Text("ScoreBoard");
        shop.setStyle("-fx-text-fill:  black");
        scoreBoard.setOnMouseClicked(event -> new ServerScoreBoardSceneMaker(getPrimaryStage()).set());

        Text customCard = new Text("Custom Card");
        customCard.setStyle("-fx-text-fill: black");
        customCard.setOnMouseClicked(event -> new CustomCardSceneMaker(getPrimaryStage()).set());


        vBox.getChildren().addAll(shop, customCard, scoreBoard);
        ScaleTool.relocate(vBox, 100, 100);
        root.getChildren().add(server);
        root.getChildren().add(vBox);

        return new MyScene(root);
    }

}
