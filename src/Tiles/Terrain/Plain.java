package Tiles.Terrain;

import Tiles.Tile;

public class Plain extends Tile {
    public Plain(int row, int col) {
        super(row, col);
        r = 0;
        g = 255;
        b = 0;
        value = 1;
    }
}
