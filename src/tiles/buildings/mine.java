package tiles.buildings;

import tiles.terrain.mountain;
import tiles.tile;
import processing.core.PImage;

public class mine extends tile {
    public static PImage img;
    public static int workersNeeded=20;
    public static int cost = 30;
    public static tile[] buildableIn;
    static {
        mountain m = new mountain(0, 0);
        m.buildAreaEnrichmentNeed = 1;
        buildableIn = new tile[]{m};
    }
    public static String buildTxt = "Enriched Mountains";
    public mine(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 105;
        b = 105;
        value = 4;
        enriched = true;
    }
}
