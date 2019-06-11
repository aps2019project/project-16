package newView.GraphicalElements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import newView.GraphicalElements.battle.Tile;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
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

    public static void transformTile(Tile tile, int row, int column) {
        double transformRatio = 8;
        double transformRatio2 = transformRatio + 1;
        double height = TILE_LENGTH * 9 * 1.05;
        double heightD2 = height / 2;
        double width = TILE_LENGTH * 5 * 1.05;
        double x = TILE_LENGTH * column * 1.05;
        double y = TILE_LENGTH * row * 1.05;
        relocate(tile
                , heightD2 + (x - heightD2) * (y + transformRatio * width) / (transformRatio2 * width)
                , y);
    }
}
