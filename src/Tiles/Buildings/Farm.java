package Tiles.Buildings;

import Tiles.Tile;
import processing.core.PImage;

public class Farm extends Tile {
    public static PImage img;
    public static int workersNeeded=10;
    public static int cost = 10;
    public static String buildableIn = "Forests, Plains &\nMountains";
    public Farm(int row, int col) {
        super(row, col, img);
        r = 0;
        g = 255;
        b = 255;
        value = 5;
    }
}
