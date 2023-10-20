package Tiles.Buildings;

import Tiles.Tile;

public class Farm extends Tile {
    public static int cost = 10;
    public Farm(int row, int col) {
        super(row, col);
        r = 0;
        g = 255;
        b = 255;
        value = 5;
    }
}
