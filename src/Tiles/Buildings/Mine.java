package Tiles.Buildings;

import Tiles.Tile;

public class Mine extends Tile {
    public static int cost = 30, buildWorkerAmt ;
    public static String buildableIn = "Forests, Plains &\nMountains";
    public Mine(int row, int col) {
        super(row, col);
        r = 255;
        g = 105;
        b = 105;
        value = 4;
        enriched = true;
    }
}
