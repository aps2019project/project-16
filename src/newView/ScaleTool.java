package newView;

import javafx.scene.Node;

import static newView.SceneMaker.SCALE;

public class ScaleTool {
    public static void relocateInScale(Node node, double x, double y) {
        node.relocate(x * SCALE, y * SCALE);
    }
}
