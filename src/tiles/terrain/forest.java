package tiles.terrain;

import tiles.tile;
import processing.core.PImage;

public class forest extends tile {
    public static PImage img;
    public forest(int row, int col){
        super(row, col, img);
        r = 1;
        g = 50;
        b = 32;
        value = 0;
    }
}
