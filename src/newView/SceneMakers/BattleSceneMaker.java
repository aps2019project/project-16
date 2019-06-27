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

    static boolean customGameBoolean = false;

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();

        BackgroundMaker.setBackgroundFor(pane, 1, "battleEntry");

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());


        ImageView storyMode = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/singlePlayer.png")));
        ImageView customGame = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/multiPlayer.png")));

        ScaleTool.resizeImageView(customGame, 100, 200);
        ScaleTool.resizeImageView(storyMode, 100, 203);

        ScaleTool.homothety(customGame, 3);
        ScaleTool.homothety(storyMode, 3);

        ScaleTool.relocate(customGame, 300, 200);
        ScaleTool.relocate(storyMode, 800, 200);

        storyMode.setOnMouseClicked(event -> {
            new GameModeSelectorSceneMaker(getPrimaryStage()).set();
            customGameBoolean = false;
        });//todo correct it
        customGame.setOnMouseClicked(event -> {
            new GameModeSelectorSceneMaker(getPrimaryStage()).set();
            customGameBoolean = true;
        });


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

        pane.getChildren().addAll(storyMode, customGame, singlePlayerText, multiPlayerText, back);

        return new MyScene(pane);
    }

    public static boolean getCustomGameBooolean() {
        return customGameBoolean;
    }
}
