package tiles.terrain;

import tiles.tile;
import processing.core.PImage;

public class plain extends tile {
    public static PImage img;
    public plain(int row, int col) {
        super(row, col, img);
        r = 0;
        g = 200;
        b = 0;
        value = 1;
    }
}
