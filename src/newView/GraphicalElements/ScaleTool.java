package newView.GraphicalElements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import static newView.SceneMakers.SceneMaker.SCALE;

public class ScaleTool {
    public static void relocate(Node node, double x, double y) {
        node.relocate(x * SCALE, y * SCALE);
    }

    public static void resizeImageView(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width * SCALE);
        imageView.setFitHeight(height * SCALE);

        imageView.setPreserveRatio(false);
    }
}
