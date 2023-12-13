package people;

import processing.core.PImage;
public class Worker extends Person {
    public static PImage img;
    public Worker(int row, int col) {
        super(row, col, img);
        r = 255;
        g = 0;
        b = 0;
        value = 0;
    }
}
