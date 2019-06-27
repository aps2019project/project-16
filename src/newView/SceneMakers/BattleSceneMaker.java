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


        ImageView storyMode = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/singlePlayer.png")));
        ImageView customMode = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/multiPlayer.png")));

        ScaleTool.resizeImageView(customMode, 100, 200);
        ScaleTool.resizeImageView(storyMode, 100, 203);

        ScaleTool.homothety(customMode, 3);
        ScaleTool.homothety(storyMode, 3);

        ScaleTool.relocate(customMode, 300, 200);
        ScaleTool.relocate(storyMode, 800, 200);

        storyMode.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage(), false).set());
        customMode.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage(), true).set());


        Text storyModeText = new Text();
        storyModeText.setText("STORY MODE");
        storyModeText.setFill(Color.WHITE);
        storyModeText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(storyModeText, 250, 520);

        Text customModeText = new Text();
        customModeText.setText("CUSTOM GAME");
        customModeText.setFill(Color.WHITE);
        customModeText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(customModeText, 750, 520);

        pane.getChildren().addAll(storyMode, customMode, storyModeText, customModeText, back);

        return new MyScene(pane);
    }
}
