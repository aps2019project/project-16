package newView.SceneMakers;

import com.dd.plist.PropertyListFormatException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.net.Client;
import models.net.requests.gameRequests.RefuseRequest;
import newView.AnimationMaker;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

public class WaitingForBattleSceneMaker extends SceneMaker {
    private static File audioPath = new File("src/newView/resources/sounds/waitingForBattle/3. 50 Cent In Da Club (2003) (www.htbeats.mihanblog.com).mp3");
    public static AudioClip waitingForBattleMusic = new AudioClip(audioPath.toURI().toString());


    public WaitingForBattleSceneMaker(Stage primaryStage) {
        super(primaryStage);
        waitingForBattleMusic.play();
    }

    private double x = 0;
    private double y = 0;

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();
        BackgroundMaker.setBackgroundFor(pane, 1, "waitingForBattle");

        setSecondBackGround();
        ImageView randomHeroImage = setHeroImageView(pane);
        Text waitingText = setWaitingForBattleText();
        ImageView back = setBackImageView();
//        StackPane runeBack = getRunes();

        StackPane rune1 = getRunes();
        ScaleTool.relocate(rune1, 100, 600 + 500);

        StackPane rune2 = getRunes();
        ScaleTool.relocate(rune2, 400, 500 + 500);

        StackPane rune3 = getRunes();
        ScaleTool.relocate(rune3, 700, 700 + 500);

        StackPane rune4 = getRunes();
        ScaleTool.relocate(rune4, 1000, 400 + 500);


        pane.getChildren().addAll(rune1, rune2, rune3, rune4);


//        pane.getChildren().add(runeBack);
        pane.getChildren().add(randomHeroImage);
        pane.getChildren().add(waitingText);
        pane.getChildren().add(back);

        return new MyScene(pane);
    }

    private ImageView setBackImageView() throws FileNotFoundException {
        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/waitingForBattleMenu/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> {
            waitingForBattleMusic.stop();
            Client.getInstance().sendPacket(new RefuseRequest());
            new MainMenuSceneMaker(getPrimaryStage()).set();
        });
        return back;
    }

    private ImageView setHeroImageView(Pane pane) throws FileNotFoundException {
        int imageNumber = Math.abs(new Random().nextInt()) % 16 + 1;
        ImageView randomHeroImage = new ImageView(new Image(new FileInputStream("src/newView/resources/waitingForBattleMenu/" + imageNumber + ".png")));
        ScaleTool.relocate(randomHeroImage, -2000 + 80, -1500);
        ScaleTool.homothety(randomHeroImage, 0.21);

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if (event.getX() < x) {
                randomHeroImage.setX(randomHeroImage.getX() + 0.12);
            } else {
                randomHeroImage.setX(randomHeroImage.getX() - 0.12);
            }

            if (event.getY() < y) {
                randomHeroImage.setY(randomHeroImage.getY() + 0.12);
            } else {
                randomHeroImage.setY(randomHeroImage.getY() - 0.12);
            }
            x = event.getX();
            y = event.getY();
        });
        return randomHeroImage;
    }

    private Text setWaitingForBattleText() {
        Text waitingText = new Text("WAITING FOR OPPONENT");
        waitingText.setStyle("-fx-font-size: 40");
        waitingText.setFill(Color.rgb(200, 200, 200, 0.8));
        ScaleTool.homothety(waitingText, 2);
        ScaleTool.relocate(waitingText, 450, 350);
        return waitingText;
    }

    private void setSecondBackGround() throws FileNotFoundException {
        ImageView secondBackground = new ImageView(new Image(new FileInputStream("src/newView/resources/waitingForBattleMenu/rift_glow_line.png")));
        ScaleTool.relocate(secondBackground, 0, 240);
        ScaleTool.resizeImageView(secondBackground, 1400, 200);
    }

    private StackPane getRunes() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        int number = Math.abs(new Random().nextInt()) % 6 + 1;
        String num = Integer.toString(number);
        String runePath = "src/newView/resources/runes/";
        ImageView rune = AnimationMaker.getSimpleAnimation(num, runePath);

        ImageView glow = new ImageView(new Image(new FileInputStream("src/newView/resources/runes/glow.png")));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(glow, rune);
        ScaleTool.homothety(stackPane, 0.4);

//        int x = Math.abs(new Random().nextInt()) % 800;
//        int y = Math.abs(new Random().nextInt()) % 1300;
//        ScaleTool.relocate(stackPane, x, y);

        KeyValue keyValue = new KeyValue(stackPane.layoutYProperty(), stackPane.getLayoutY() - 1000);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(160000), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();


        return stackPane;
    }
}
