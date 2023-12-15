package tiles.buildings;

import tiles.Tile;
import processing.core.PImage;

public class Village extends Tile {
    public static PImage img;
    public Village(int row, int col) {
        super(row, col, img);
        r = 200;
        g = 0;
        b = 200;
        value = 3;
    }
}
