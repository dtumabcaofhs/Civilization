package Tiles;

import processing.core.PApplet;

public class Tile {
    public int value;
    public int row, col;
    protected int r,g,b;
    protected int rB,gB,bB;
    public boolean enriched;
    public boolean selected;
    private boolean brightCalculated = false;

    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    public void draw(PApplet window){
        if(!brightCalculated){
            calcBrightness();
            brightCalculated = true;
        }
        window.noStroke();
        window.stroke(0);
        if(selected) {
            window.fill(rB,gB,bB);
        }else{
            window.fill(r,g,b);
        }
        window.rect(row * 100, col * 100, 100, 100);
        if(enriched) {
            window.fill(255,255,0);
            window.ellipse(row*100 + 80,col*100 + 20,20,20);
        }
    }

    public void calcBrightness(){
        //if using images, change this to overlay bright rect
            double change = 1.3; //brightness change
            if(r * change > 255){
                rB = 255;
            }else{
                rB = (int) (r * change);
            }

            if(g * change > 255){
                gB = 255;
            }else{
                gB = (int)(g * change);
            }

            if(b * change > 255){
                bB = 255;
            }else{
                bB = (int)(b * change);
            }
        }
    }
