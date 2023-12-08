package tiles.buildings;

import tiles.tile;
import processing.core.PImage;

public class city extends tile {
    public static PImage img;
    public city(int row, int col) {
        super(row, col, img);
        r = 200;
        g = 0;
        b = 200;
        value = 3;
    }
}
