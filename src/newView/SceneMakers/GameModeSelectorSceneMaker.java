package newView.SceneMakers;

import controllers.CustomGameController;
import controllers.StoryController;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import view.views.CustomGameView;
import view.views.StoryView;

import java.io.FileInputStream;

public class GameModeSelectorSceneMaker extends SceneMaker {
    private boolean customGame;
    private TextField enterDeckName;

    public GameModeSelectorSceneMaker(Stage primaryStage, boolean customGame) {
        super(primaryStage);
        this.customGame = customGame;
    }

    @Override
    public Scene makeScene() throws Exception {

        Pane pane = new Pane();

        BackgroundMaker.setBackgroundFor(pane, 1, "gameModeSelector");
        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> new BattleSceneMaker(getPrimaryStage()).set());

        ImageView killingHero = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/killingHero.png")));
        ScaleTool.relocate(killingHero, 250, 100);
        ScaleTool.resizeImageView(killingHero, 250, 600);
        Text killingHeroText = new Text();
        killingHeroText.setText("KILLING HERO");
        killingHeroText.setStyle("-fx-font-size:  30");
        ScaleTool.relocate(killingHeroText, 280, 550);
        killingHeroText.setFill(Color.WHITE);


        ImageView collectFlag = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/collectFlag.png")));
        ScaleTool.relocate(collectFlag, 500, 100);
        ScaleTool.resizeImageView(collectFlag, 250, 600);
        Text collectFlagText = new Text();
        collectFlagText.setText("COLLECT FLAG");
        collectFlagText.setStyle("-fx-font-size: 25");
        ScaleTool.relocate(collectFlagText, 540, 600);
        collectFlagText.setFill(Color.WHITE);


        ImageView holdFlag = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/holdFlag.png")));
        ScaleTool.relocate(holdFlag, 750, 100);
        ScaleTool.resizeImageView(holdFlag, 250, 600);
        Text holdFlagText = new Text();
        holdFlagText.setText("HOLD FLAG");
        holdFlagText.setFill(Color.WHITE);
        holdFlagText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(holdFlagText, 795, 570);

        enterDeckName = new TextField();
        enterDeckName.setPromptText("OPP DECK NAME");
        enterDeckName.setStyle("-fx-prompt-text-fill: gray");
        ScaleTool.relocate(enterDeckName, 530, 50);
        enterDeckName.setPrefColumnCount(15);

        setActions(killingHero, collectFlag, holdFlag);

        pane.getChildren().addAll(back, killingHero, collectFlag, holdFlag);
        pane.getChildren().addAll(killingHeroText, collectFlagText, holdFlagText);
        if (customGame)
            pane.getChildren().add(enterDeckName);

        return new MyScene(pane);
    }

    private void setActions(ImageView killingHero, ImageView collectFlag, ImageView holdFlag) {
        if (!customGame) {
            killingHero.setOnMouseClicked(event -> {
                new StoryController(new StoryView()).loadLevel(1);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
            holdFlag.setOnMouseClicked(event -> {
                new StoryController(new StoryView()).loadLevel(2);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
            collectFlag.setOnMouseClicked(event -> {
                new StoryController(new StoryView()).loadLevel(3);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
        } else {
            killingHero.setOnMouseClicked(event -> {
                new CustomGameController(new CustomGameView()).startGame(enterDeckName.getText(), 1, 0);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
            holdFlag.setOnMouseClicked(event -> {
                new CustomGameController(new CustomGameView()).startGame(enterDeckName.getText(), 2, 1);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
            collectFlag.setOnMouseClicked(event -> {
                TextInputDialog numberOfFlags = new TextInputDialog();
                numberOfFlags.setHeaderText("Enter number of flags");
                numberOfFlags.setContentText("flags:");
                int flags = Integer.parseInt(numberOfFlags.showAndWait().get());
                new CustomGameController(new CustomGameView()).startGame(enterDeckName.getText(), 3, flags);
                new InGameSceneMaker(getPrimaryStage()).set();
                BattleSceneMaker.battleBgSound.stop();
            });
        }
    }
}
