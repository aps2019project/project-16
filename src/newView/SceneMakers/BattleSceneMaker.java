package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;

public class BattleSceneMaker extends SceneMaker {
    public BattleSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();

        BackgroundMaker.setBackgroundFor(pane, 1, "battleEntry");

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());


        ImageView singlePlayer = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/singlePlayer.png")));
        ImageView multiPlayer = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/multiPlayer.png")));

        ScaleTool.resizeImageView(multiPlayer, 100, 200);
        ScaleTool.resizeImageView(singlePlayer, 100, 203);

        ScaleTool.homothety(multiPlayer, 3);
        ScaleTool.homothety(singlePlayer, 3);

        ScaleTool.relocate(multiPlayer, 300, 200);
        ScaleTool.relocate(singlePlayer, 800, 200);

        singlePlayer.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage()).set());//todo correct it
        multiPlayer.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage()).set());


        Text singlePlayerText = new Text();
        singlePlayerText.setText("STORY MODE");
        singlePlayerText.setFill(Color.WHITE);
        singlePlayerText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(singlePlayerText, 250, 520);

        Text multiPlayerText = new Text();
        multiPlayerText.setText("CUSTOM GAME");
        multiPlayerText.setFill(Color.WHITE);
        multiPlayerText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(multiPlayerText, 750, 520);

        pane.getChildren().addAll(singlePlayer, multiPlayer, singlePlayerText, multiPlayerText, back);

        return new MyScene(pane);
    }
}
