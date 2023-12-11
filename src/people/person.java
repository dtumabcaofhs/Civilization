package people;

import processing.core.PApplet;
import processing.core.PImage;

public class person {
    PImage img;
    public static int w = 40, h = 40;
    public int row, col, x, y;
    public int r;
    public int g;
    public int b;
    protected int rB,gB,bB;
    public int value;
    public boolean selected;
    public int movementNum;
    boolean brightCalculated = false;
    boolean useImages = true;
    public person(int row, int col, PImage img) {
        this.row = row;
        this.col = col;
        System.out.println(row+","+col);
        this.x = row * 100 + 30;
        this.y = col * 100 + 30;
        this.selected = false;
    }

    public void draw(PApplet window){
        if (!brightCalculated) {
            calcBrightness();
            brightCalculated = true;
        }

        if(selected) {
            if (img == null || !useImages) {
                window.fill(rB, gB, bB);
            }else {
                window.tint(255, 150);
            }
        }else{
            if (img == null || !useImages) {
                window.fill(r, g, b);
            } else {
                window.noTint();
            }
        }

        if(useImages){
            System.out.println("yee");
            if (img != null) {
                window.image(img, x, y, w, h);
            }else{
                window.rect(x, y, w, h);
            }
        }else{
            //window.rect(x, y, w, h);
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
