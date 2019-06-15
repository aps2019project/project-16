package newView.GraphicalElements.battle;

import javafx.scene.layout.AnchorPane;
import newView.GraphicalElements.ScaleTool;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class TilesPane extends AnchorPane {
    private Tile[][] tiles;
    private final static int NUMBER_OF_ROWS = 5;
    private final static int NUMBER_OF_COLUMNS = 9;

    public TilesPane() {
        super();
        ScaleTool.relocate(this
                , WIDTH / 2 - TILE_LENGTH * ((int)(NUMBER_OF_COLUMNS / 2) + 1)
                , HEIGHT / 2 - TILE_LENGTH * ((int)(NUMBER_OF_ROWS / 2) + 1));
        initTiles();
    }

    private void initTiles() {
        tiles = new Tile[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                Tile tile = new Tile(i, j);
                tiles[i][j] = tile;
                this.getChildren().add(tile);
            }
        }
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
}
