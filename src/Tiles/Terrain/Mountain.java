package Tiles.Terrain;

import Tiles.Tile;

public class Mountain extends Tile {
    public Mountain(int row, int col) {
        super(row, col);
        r = 105;
        g = 105;
        b = 105;
        value = 2;
    }
}
