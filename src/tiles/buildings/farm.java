package tiles.buildings;

import tiles.terrain.forest;
import tiles.terrain.mountain;
import tiles.terrain.plain;
import tiles.tile;
import processing.core.PImage;

public class farm extends tile {
    public static PImage img;
    public static int workersNeeded=10;
    public static int cost = 10;
    public static tile[] buildableIn;
    static {
        forest f = new forest(0, 0);
        f.buildAreaEnrichmentNeed = 0;
        plain p = new plain(0, 0);
        p.buildAreaEnrichmentNeed = 0;
        mountain m = new mountain(0, 0);
        m.buildAreaEnrichmentNeed = 0;
        buildableIn = new tile[]{f,p,m};
    }
    public static String buildTxt = "Forests, Plains &\nMountains";
    public farm(int row, int col) {
        super(row, col, img);
        r = 0;
        g = 255;
        b = 255;
        value = 5;
    }
}
