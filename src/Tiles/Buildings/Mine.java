package Tiles.Buildings;

import Tiles.Terrain.Mountain;
import Tiles.Tile;
import processing.core.PImage;

public class Mine extends Tile {
    public static PImage img;
    public static int workersNeeded=20;
    public static int cost = 30;
    public static Tile[] buildableIn;
    static {
        Mountain m = new Mountain(0, 0);
        m.buildAreaEnrichmentNeed = 1;
        buildableIn = new Tile[]{m};
    }
    public static String buildTxt = "Enriched Mountains";
    public Mine(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 105;
        b = 105;
        value = 4;
        enriched = true;
    }
}
