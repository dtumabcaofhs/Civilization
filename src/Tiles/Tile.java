package Tiles;

import processing.core.PApplet;

public class Tile {
    protected int row, col;
    protected int r,g,b;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void draw(PApplet window){
        window.fill(r,g,b);
        window.rect(row*100,col*100,100,100);
    }
}
