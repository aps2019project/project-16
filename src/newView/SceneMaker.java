package newView;

import javafx.scene.Scene;

public abstract class SceneMaker {
    public static final double SCALE = 1;
    public static final double HEIGHT = 1000;
    public static final double WIDTH = 600;


    public abstract Scene makeScene();
}
