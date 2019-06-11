package newView.SceneMakers;

import javafx.scene.Scene;

import java.io.FileNotFoundException;

public abstract class SceneMaker {
    public static final double SCALE = 1;
    public static final double HEIGHT = 700;
    public static final double WIDTH = 1300;

    public abstract Scene makeScene() throws FileNotFoundException;
}
