package Tiles.Buildings;

import Tiles.Tile;
import processing.core.PImage;

public class Lumberyard extends Tile {
    public static PImage img;
    public static int workersNeeded=5;
    public static int cost = 5;
    public static String buildableIn = "Forests";
    public Lumberyard(int row, int col) {
        super(row, col, img);
        r = 139;
        g = 69;
        b = 19;
        value = 7;
    }
}
