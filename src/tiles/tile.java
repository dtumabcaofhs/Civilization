package tiles;

import processing.core.PApplet;
import processing.core.PImage;

public class tile {
    PImage img;
    public static PImage enrichedImg;
    public int value;
    public int row, col, x, y;
    public static int w = 100, h = 100;
    protected int r,g,b;
    protected int rB,gB,bB;
    public boolean enriched;
    public int buildAreaEnrichmentNeed; //0: dont care 1: enriched 2: not enriched
    public boolean hoveredOver;
    boolean brightCalculated = false;
    boolean useImages = true;

    public tile(int row, int col, PImage img){
        this.row = row;
        this.col = col;

        this.x = row * w;
        this.y = col * h;
        this.img = img;
    }

    public void draw(PApplet window, boolean canBuildOnHover, tile buildType){
        if (!brightCalculated) {
            calcBrightness();
            brightCalculated = true;
        }

        if(hoveredOver) {
            if (img == null || !useImages) {
                window.fill(rB, gB, bB);
            } else if(buildType != null){
                if (canBuildOnHover) {
                    window.tint(0,255,0, 150);
                }else{
                    window.tint(255,0,0, 150);
                }
            }else{
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
            if (img != null) {
                window.image(img, x, y, w, h);
            }else{
                window.rect(x, y, w, h);
            }
        }else{
            window.rect(x, y, w, h);
        }

        if(enriched) {
            //window.fill(255,255,0);
            //window.ellipse(x + 80, y + 20,20,20);
            window.image(enrichedImg,x+80,y,20,20);
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
