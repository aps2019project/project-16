package newView.GraphicalElements.battle;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import newView.GraphicalElements.ScaleTool;

import java.io.FileNotFoundException;

import static newView.GraphicalElements.battle.Tile.TILE_LENGTH;
import static newView.SceneMakers.SceneMaker.SCALE;

public class TilesPane extends BorderPane {
    private Tile[][] tiles;

    public TilesPane() throws FileNotFoundException {
        super();
        initTiles();
    }

    private Tile[][] initTiles() throws FileNotFoundException {
        tiles = new Tile[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                Tile tile = new Tile();
                ScaleTool.transformTile(tile, i, j);
                tiles[i][j] = tile;
                this.getChildren().add(tile);
            }
        }
        return tiles;
    }
}
