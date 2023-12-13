package tiles.terrain;

import tiles.Tile;
import processing.core.PImage;

public class Plain extends Tile {
    public static PImage img;
    public Plain(int row, int col) {
        super(row, col, img);
        r = 0;
        g = 200;
        b = 0;
        value = 1;
    }
}
