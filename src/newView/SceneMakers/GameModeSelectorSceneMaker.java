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

public class GameModeSelectorSceneMaker extends SceneMaker {
    public GameModeSelectorSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {

        Pane pane = new Pane();

        BackgroundMaker.setBackgroundFor(pane, 1, "gameModeSelector");
        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);

        ImageView killingHero = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/killingHero.png")));
        ScaleTool.relocate(killingHero, 250, 100);
        ScaleTool.resizeImageView(killingHero, 250, 600);
        Text killingHeroText = new Text();
        killingHeroText.setText("KILLING HERO");
        killingHeroText.setFill(Color.WHITE);


        ImageView collectFlag = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/collectFlag.png")));
        ScaleTool.relocate(collectFlag, 500, 100);
        ScaleTool.resizeImageView(collectFlag, 250, 600);
        Text collectFlagText = new Text();
        collectFlagText.setText("COLLECT FLAG");
        collectFlagText.setStyle("-fx-font-size: 30");
        collectFlagText.setFill(Color.WHITE);


        ImageView holdFlag = new ImageView(new Image(new FileInputStream("src/newView/resources/gameModeSelector/holdFlag.png")));
        ScaleTool.relocate(holdFlag, 750, 100);
        ScaleTool.resizeImageView(holdFlag, 250, 600);
        Text holdFlagText = new Text();
        holdFlagText.setText("HOLD FLAG");
        holdFlagText.setFill(Color.WHITE);
        holdFlagText.setStyle("-fx-font-size: 30");
        ScaleTool.relocate(holdFlagText, 795 , 570);

        pane.getChildren().addAll(back, killingHero, collectFlag, holdFlag);
        pane.getChildren().addAll(killingHeroText, collectFlagText, holdFlagText);

        return new MyScene(pane);
    }
}
