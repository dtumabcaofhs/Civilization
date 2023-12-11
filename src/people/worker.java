package people;

import processing.core.PImage;

public class worker extends person {
    public static PImage img;
    public worker(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 0;
        b = 0;
        value = 0;
    }
}
