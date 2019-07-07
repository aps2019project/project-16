package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.net.Client;
import models.net.requests.gameRequests.MultiPlayerGameRequest;
import models.net.requests.gameRequests.RefuseRequest;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class WaitingForBattleSceneMaker extends SceneMaker {
    public WaitingForBattleSceneMaker(Stage primaryStage) {
        super(primaryStage);
        Client.getInstance().sendPacket(new MultiPlayerGameRequest());
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

        pane.getChildren().add(randomHeroImage);
        pane.getChildren().add(waitingText);
        pane.getChildren().add(back);

        return new MyScene(pane);
    }

    private ImageView setBackImageView() throws FileNotFoundException {
        ImageView back = new ImageView(new Image(new FileInputStream("src/newView/resources/waitingForBattleMenu/back.png")));
        ScaleTool.resizeImageView(back, 85, 85);
        back.setOnMouseClicked(event -> {
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
}
