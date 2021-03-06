package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.gameRequests.MultiPlayerGameRequest;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.File;
import java.io.FileInputStream;

public class BattleSceneMaker extends SceneMaker {
    public static AudioClip battleBgSound = new AudioClip(new File("src/newView/resources/sounds/battle/battlebg.mp3").toURI().toString());

    public BattleSceneMaker(Stage primaryStage) {
        super(primaryStage);
        if (!battleBgSound.isPlaying())
            battleBgSound.play();
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();

        BackgroundMaker.setBackgroundFor(pane, 1, "battleEntry");

        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> {
            new MainMenuSceneMaker(getPrimaryStage()).set();
            battleBgSound.stop();
        });


        ImageView customGame = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/customGame.png")));
        ImageView storyMode = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/storyMode.png")));
        ImageView multiPlayer = new ImageView(new Image(new FileInputStream("src/newView/resources/battleEntry/multiPlayer.png")));


        ScaleTool.resizeImageView(storyMode, 100, 200);
        ScaleTool.resizeImageView(customGame, 100, 203);
        ScaleTool.resizeImageView(multiPlayer, 100, 203);


        ScaleTool.homothety(storyMode, 2.8);
        ScaleTool.homothety(customGame, 2.8);
        ScaleTool.homothety(multiPlayer, 3.1);

        ScaleTool.relocate(storyMode, 200, 250);
        ScaleTool.relocate(customGame, 900, 250);
        ScaleTool.relocate(multiPlayer, 550, 250);

        storyMode.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage(), false).set());
        customGame.setOnMouseClicked(event -> new GameModeSelectorSceneMaker(getPrimaryStage(), true).set());
        multiPlayer.setOnMouseClicked(event -> {
            new WaitingForBattleSceneMaker(getPrimaryStage()).set();
            Client.getInstance().sendPacket(new MultiPlayerGameRequest());
            BattleSceneMaker.battleBgSound.stop();
        });

        Text storyModeText = new Text();
        storyModeText.setText("STORY MODE");
        storyModeText.setFill(Color.WHITE);
        storyModeText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(storyModeText, 160, 555);

        Text customGameText = new Text();
        customGameText.setText("CUSTOM GAME");
        customGameText.setFill(Color.WHITE);
        customGameText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(customGameText, 847, 560);

        Text multiPlayerText = new Text();
        multiPlayerText.setText("MULTIPLAYER");
        multiPlayerText.setFill(Color.WHITE);
        multiPlayerText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(multiPlayerText, 515, 580);

        pane.getChildren().addAll(customGame, storyMode, customGameText, storyModeText, back, multiPlayer, multiPlayerText);

        return new MyScene(pane);
    }
}
