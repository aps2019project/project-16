package newView.GraphicalElements.battle;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import newView.GraphicalElements.ScaleTool;

public class Tile extends Pane {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private boolean isSelected = false;
    private TilePolygon polygon = new TilePolygon();
    private ImageView imageView;
    public static final double NORMAL_OPACITY = 0.2;
    public static final double HOVER_OPACITY = 0.4;
    public static final double SELECTED_OPACITY = 0.6;
    public static final double TILE_LENGTH = 80;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
        ScaleTool.relocateTile(this);
        ScaleTool.makeTilePoints(polygon, row, column);
        this.getChildren().add(polygon);
        setMouseEventsFor(polygon);
    }

    public void setImageView(ImageView imageView) {
        if (this.imageView != null) {
            this.getChildren().remove(this.imageView);
        }
        this.imageView = imageView;
        if (imageView != null) {
            ScaleTool.resizeImageView(imageView, TILE_LENGTH, TILE_LENGTH);
            this.getChildren().add(imageView);
            setMouseEventsFor(imageView);
        }
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected) {
                polygon.setOpacity(HOVER_OPACITY);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected) {
                polygon.setOpacity(NORMAL_OPACITY);
            }
        });
        node.setOnMouseClicked(event -> {
            isSelected = !isSelected;
            if (isSelected) {
                polygon.setOpacity(SELECTED_OPACITY);
            } else {
                polygon.setOpacity(HOVER_OPACITY);
            }
        });
    }
}
