package newView.SceneMakers;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            primaryStage.setScene(makeScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
