package Tiles.Buildings;

import Tiles.Terrain.Forest;
import Tiles.Terrain.Mountain;
import Tiles.Terrain.Plain;
import Tiles.Tile;
import processing.core.PImage;

public class Laboratory extends Tile {
    public static PImage img;
    public static int workersNeeded=10;
    public static int cost = 100;
    public static Tile[] buildableIn;
    static {
        Forest f = new Forest(0, 0);
        f.buildAreaEnrichmentNeed = 1;
        Plain p = new Plain(0, 0);
        p.buildAreaEnrichmentNeed = 1;
        Mountain m = new Mountain(0, 0);
        m.buildAreaEnrichmentNeed = 1;
        buildableIn = new Tile[]{f,p,m};
    }
    public static String buildTxt = "Enriched Forests,\nPlains & Mountains";
    public Laboratory(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 127;
        b = 80;
        value = 6;
    }
}
