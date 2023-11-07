package Tiles;

import processing.core.PApplet;
import processing.core.PImage;

public class Tile {
    protected PImage img;
    public int value;
    public int row, col;
    protected int r,g,b;
    protected int rB,gB,bB;
    public boolean enriched;
    public boolean selected;
    boolean brightCalculated = false;
    boolean useImages = true;

    public Tile(int row, int col, PImage img){
        this.row = row;
        this.col = col;
        this.img = img;
    }

    public void draw(PApplet window){
        if (!brightCalculated) {
            calcBrightness();
            brightCalculated = true;
        }

        window.noStroke();

        if(selected) {
            if(img == null || !useImages) {
                window.fill(rB, gB, bB);
            }else{
                window.tint(255,100);
            }
        }else{
            if(img == null || !useImages) {
                window.fill(r, g, b);
            }else{
                window.noTint();
            }
        }

        if(useImages){
            if (img != null) {
                window.image(img, row * 100, col * 100, 100, 100);
            }else{
                window.rect(row * 100, col * 100, 100, 100);
            }
        }else{
            window.rect(row * 100, col * 100, 100, 100);
        }

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

            if(this.g * change > 255){
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
