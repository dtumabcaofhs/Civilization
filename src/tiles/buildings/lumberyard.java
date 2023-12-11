package tiles.buildings;

import tiles.terrain.forest;
import tiles.tile;
import processing.core.PImage;

public class lumberyard extends tile {
    public static PImage img;
    public static int workersNeeded=5;
    public static int cost = 5;
    public static tile[] buildableIn;
    static {
        forest f = new forest(0, 0);
        f.buildAreaEnrichmentNeed = 0;
        buildableIn = new tile[]{f};
    }
    public static String buildTxt = "Forests";
    public lumberyard(int row, int col) {
        super(row, col, img);
        r = 139;
        g = 69;
        b = 19;
        value = 7;
    }
}
