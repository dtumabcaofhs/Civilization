package tiles.terrain;

import tiles.tile;
import processing.core.PImage;

public class mountain extends tile {
    public static PImage img;
    public mountain(int row, int col) {
        super(row, col, img);
        r = 105;
        g = 105;
        b = 105;
        value = 2;
    }
}
