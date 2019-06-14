package newView.GraphicalElements.battle;

import javafx.scene.layout.AnchorPane;
import newView.GraphicalElements.ScaleTool;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class TilesPane extends AnchorPane {
    private Tile[][] tiles;

    public TilesPane() {
        super();
        ScaleTool.relocate(this, WIDTH / 2 - TILE_LENGTH * 5, HEIGHT / 2 - TILE_LENGTH * 3);
        initTiles();
    }

    private void initTiles() {
        tiles = new Tile[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
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
