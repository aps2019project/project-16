package newView.SceneMakers;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class SceneMaker {
    public static final double SCALE = 1;
    public static final double HEIGHT = 700;
    public static final double WIDTH = 1300;
    private static FileInputStream mouseFIS;
    public static final ImageCursor GAME_CURSOR;
    private Stage primaryStage;

    public SceneMaker(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    static {
        try {
            mouseFIS = new FileInputStream("src/newView/resources/mouse.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GAME_CURSOR = new ImageCursor(new Image(mouseFIS));
    }

    public abstract Scene makeScene() throws Exception;

    public void set() {
        try {
            Scene scene = makeScene();
            scene.setCursor(GAME_CURSOR);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    protected void setGlowOnMouseOver(Text text) {
        text.setOnMouseEntered(event -> text.setEffect(new Glow(0.8)));
        text.setOnMouseExited(event -> text.setEffect(null));
    }
}
