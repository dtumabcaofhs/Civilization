package Tiles.Buildings;

import Tiles.Tile;

public class City extends Tile {
    public City(int row, int col) {
        super(row, col);
        r = 255;
        g = 0;
        b = 255;
        value = 3;
    }
}
