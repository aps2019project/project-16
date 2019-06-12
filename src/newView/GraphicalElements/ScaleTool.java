package newView.GraphicalElements;

import javafx.scene.Node;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import newView.GraphicalElements.battle.Tile;
import newView.GraphicalElements.battle.TilesPane;


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

    public static void resizeRectangle(Rectangle rectangle, double width, double height) {
        rectangle.setWidth(width * SCALE);
        rectangle.setHeight(height * SCALE);
    }

    public static void resizeRegion(Region region, double width, double height) {
        region.setMinWidth(width * SCALE);
        region.setMaxWidth(width * SCALE);
        region.setPrefWidth(width * SCALE);

        region.setMinHeight(height * SCALE);
        region.setMaxHeight(height * SCALE);
        region.setPrefHeight(height * SCALE);
    }

    private static final double TRANSFORM_RATIO = 8;
    private static final double TRANSFORM_RATIO_2 = TRANSFORM_RATIO + 1;
    private static final double HEIGHT = TILE_LENGTH * 9;
    private static final double HEIGHT_D_2 = HEIGHT / 2;
    private static final double WIDTH = TILE_LENGTH * 5;

    public static void transformTile(Tile tile, int row, int column) {
        double x = TILE_LENGTH * column * 1.05;
        double y = TILE_LENGTH * row * 1.05;
        relocate(tile
                , HEIGHT_D_2 + (x - HEIGHT_D_2) * (y + TRANSFORM_RATIO * WIDTH) / (TRANSFORM_RATIO_2 * WIDTH)
                , y);

        PerspectiveTransform perspective = new PerspectiveTransform();
        perspective.setUly(0);
        perspective.setUry(0);
        perspective.setLry(0 + TILE_LENGTH);
        perspective.setLly(0 + TILE_LENGTH);

        perspective.setUlx(getNewX(x, y) - x);
        perspective.setUrx(getNewX(x + TILE_LENGTH, y) - x);
        perspective.setLlx(getNewX(x, y + TILE_LENGTH) - x);
        perspective.setLrx(getNewX(x + TILE_LENGTH, y + TILE_LENGTH) - x);

        tile.setEffect(perspective);
    }

    private static double getNewX(double x, double y) {
        return HEIGHT_D_2 + (x - HEIGHT_D_2) * (y + TRANSFORM_RATIO * WIDTH) / (TRANSFORM_RATIO_2 * WIDTH);
    }

    public static void transformTilesPane(TilesPane pane) {
        PerspectiveTransform perspective = new PerspectiveTransform();

        perspective.setUly(0);
        perspective.setUry(0);
        perspective.setLly(WIDTH);
        perspective.setLry(WIDTH);

        perspective.setUlx(getNewX(0, 0));
        perspective.setUrx(getNewX(HEIGHT, 0));
        perspective.setLlx(getNewX(0, WIDTH));
        perspective.setLrx(getNewX(HEIGHT, WIDTH));

        pane.setEffect(perspective);
    }
}
