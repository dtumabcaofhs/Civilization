package People;

import processing.core.PApplet;

public class Person {
    public int row, col;
    public int value;
    public int r, g, b;
    public boolean selected;
    public int movementNum;
    public Person(int row, int col) {
        this.row = row;
        this.col = col;
        selected = false;
    }

    public void draw(PApplet window){
        window.fill(r, g, b);
        window.rect((row * 100) + 30, (col * 100) + 30, 40, 40);
    }
}
