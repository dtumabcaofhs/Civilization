package Tiles.Terrain;

import Tiles.Tile;

public class Forest extends Tile {
    public Forest(int row, int col){
        super(row, col);
        r = 1;
        g = 50;
        b = 32;
        value = 0;
    }
}
