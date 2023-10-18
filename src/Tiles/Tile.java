package Tiles;

import processing.core.PApplet;

public class Tile {
    public int value;
    public int row, col;
    protected int r,g,b;
    public boolean enriched;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void draw(PApplet window){
        window.fill(r,g,b);
        window.rect(row*100,col*100,100,100);
        if(enriched) {
            window.fill(255,255,0);
            window.ellipse(row*100 + 80,col*100 + 20,20,20);
        }
    }
}
