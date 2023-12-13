package tiles.buildings;

import tiles.Tile;
import tiles.terrain.Forest;
import processing.core.PImage;

public class Lumberyard extends Tile {
    public static PImage img;
    public static int workersNeeded=5;
    public static int cost = 5;
    public static Tile[] buildableIn;
    static {
        Forest f = new Forest(0, 0);
        f.buildAreaEnrichmentNeed = 0;
        buildableIn = new Tile[]{f};
    }
    public static String buildTxt = "Forests";
    public Lumberyard(int row, int col) {
        super(row, col, img);
        r = 139;
        g = 69;
        b = 19;
        value = 7;
    }
}
