package newView.GraphicalElements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import newView.GraphicalElements.battle.Tile;
import newView.GraphicalElements.battle.TilePolygon;


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
    private static final double HEIGHT = TILE_LENGTH * 9 * SCALE;
    private static final double HEIGHT_D_2 = HEIGHT / 2;
    private static final double WIDTH = TILE_LENGTH * 5 * SCALE;

    public static void relocateTile(Tile tile) {
        double x = TILE_LENGTH * tile.getColumn();
        double y = TILE_LENGTH * tile.getRow();

        relocate(tile, getNewX(x, y), y);
    }

    public static void makeTilePoints(TilePolygon polygon, int row, int column) {
        double x = TILE_LENGTH * column;
        double y = TILE_LENGTH * row;

        polygon.setUly(0);
        polygon.setUry(0);
        polygon.setLly(0 + TILE_LENGTH);
        polygon.setLry(0 + TILE_LENGTH);

        double coordinateX = getNewX(x, y);
        polygon.setUlx(getNewX(x, y) - coordinateX);
        polygon.setUrx(getNewX(x + TILE_LENGTH, y) - coordinateX);
        polygon.setLlx(getNewX(x, y + TILE_LENGTH) - coordinateX);
        polygon.setLrx(getNewX(x + TILE_LENGTH, y + TILE_LENGTH) - coordinateX);

        polygon.setPoints();
    }

    private static double getNewX(double x, double y) {
        return HEIGHT_D_2 + (x - HEIGHT_D_2) * (y + TRANSFORM_RATIO * WIDTH) / (TRANSFORM_RATIO_2 * WIDTH);
    }
}
