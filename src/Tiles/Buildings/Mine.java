package Tiles.Buildings;

import Tiles.Tile;

public class Mine extends Tile {
    public static int workersNeeded=20;
    public static int cost = 30;
    public static String buildableIn = "Enriched Mountains";
    public Mine(int row, int col) {
        super(row, col);
        r = 255;
        g = 105;
        b = 105;
        value = 4;
        enriched = true;
    }
}
