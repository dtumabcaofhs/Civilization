package Tiles.Buildings;

import Tiles.Tile;

public class Laboratory extends Tile {
    public static int workersNeeded=10;
    public static int cost = 100;
    public static String buildableIn = "Enriched Forests,\nPlains & Mountains";
    public Laboratory(int row, int col) {
        super(row, col);
        r = 255;
        g = 127;
        b = 80;
        value = 6;
    }
}