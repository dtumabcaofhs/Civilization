package Tiles.Terrain;

import Tiles.Tile;
import processing.core.PImage;

public class Forest extends Tile {
    public static PImage img;
    public Forest(int row, int col){
        super(row, col, img);
        r = 1;
        g = 50;
        b = 32;
        value = 0;
    }
}
