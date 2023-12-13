package tiles.terrain;

import tiles.Tile;
import processing.core.PImage;

public class Mountain extends Tile {
    public static PImage img;
    public Mountain(int row, int col) {
        super(row, col, img);
        r = 105;
        g = 105;
        b = 105;
        value = 2;
    }
}
