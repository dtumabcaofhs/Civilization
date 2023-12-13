package tiles.buildings;

import tiles.Tile;
import tiles.terrain.Forest;
import tiles.terrain.Mountain;
import tiles.terrain.Plain;
import processing.core.PImage;

public class Farm extends Tile {
    public static PImage img;
    public static int workersNeeded=10;
    public static int cost = 10;
    public static Tile[] buildableIn;
    static {
        Forest f = new Forest(0, 0);
        f.buildAreaEnrichmentNeed = 0;
        Plain p = new Plain(0, 0);
        p.buildAreaEnrichmentNeed = 0;
        Mountain m = new Mountain(0, 0);
        m.buildAreaEnrichmentNeed = 0;
        buildableIn = new Tile[]{f,p,m};
    }
    public static String buildTxt = "Forests, Plains &\nMountains";
    public Farm(int row, int col) {
        super(row, col, img);
        r = 0;
        g = 255;
        b = 255;
        value = 5;
    }
}
