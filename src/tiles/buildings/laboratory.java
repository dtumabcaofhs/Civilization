package tiles.buildings;

import tiles.terrain.forest;
import tiles.terrain.mountain;
import tiles.terrain.plain;
import tiles.tile;
import processing.core.PImage;

public class laboratory extends tile {
    public static PImage img;
    public static int workersNeeded=10;
    public static int cost = 100;
    public static tile[] buildableIn;
    static {
        forest f = new forest(0, 0);
        f.buildAreaEnrichmentNeed = 1;
        plain p = new plain(0, 0);
        p.buildAreaEnrichmentNeed = 1;
        mountain m = new mountain(0, 0);
        m.buildAreaEnrichmentNeed = 1;
        buildableIn = new tile[]{f,p,m};
    }
    public static String buildTxt = "Enriched Forests,\nPlains & Mountains";
    public laboratory(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 127;
        b = 80;
        value = 6;
    }
}
