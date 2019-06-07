package newView;

import javafx.scene.Scene;

import java.io.FileNotFoundException;

public abstract class SceneMaker {
    public static final double SCALE = 1;
    public static final double HEIGHT = 700 * SCALE;
    public static final double WIDTH = 1300 * SCALE;


    public abstract Scene makeScene() throws FileNotFoundException;
}
